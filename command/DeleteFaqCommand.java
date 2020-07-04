package command;

import classes.AccessUser;
import classes.RoleUser;
import classes.Roles;
import classes.UserService;
import command.creater.RoutingManager;
import entity.Faq;
import entity.User;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.FaqFacade;

public class DeleteFaqCommand implements ActionCommand {

    private FaqFacade faqFacade;

    public DeleteFaqCommand() {
        try {
            Context context;
            context = new InitialContext();
            faqFacade = (FaqFacade) context.lookup("java:module/FaqFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DeleteFaqCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null){
           return RoutingManager.getRoute("path.page.login"); 
        }
        
        AccessUser au = new AccessUser(); 
        User regUser = au.onAccess(request); 
        if(regUser==null) {
            return RoutingManager.getRoute("path.page.login");
        }
        RoleUser roleUser = new RoleUser(); 
        String role = roleUser.getRole(regUser); 
        request.setAttribute("role", role);
        
        UserService us = new UserService();
        if(!us.userContainRole(Roles.SUPPORT.toString(), regUser)){
            String page = RoutingManager.getRoute("path.page.login");
            return page;
        }
        
        String id = request.getParameter("id");
        try {
            Faq deleteFaq = faqFacade.find(new Long(id));
            faqFacade.remove(deleteFaq);
            request.setAttribute("info", "Вопрос удален успешно");
        }catch(Exception e){
            request.setAttribute("info", "Вопрос не удалось удалить");
        }
        
        request.setAttribute("faq", faqFacade.findAll());
        
        return RoutingManager.getRoute("path.page.allFaq");
    }
}