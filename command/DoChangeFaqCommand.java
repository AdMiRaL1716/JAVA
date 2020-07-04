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

public class DoChangeFaqCommand implements ActionCommand {

    private FaqFacade faqFacade;

    public DoChangeFaqCommand() {
        try {
            Context context;
            context = new InitialContext();
            faqFacade = (FaqFacade) context.lookup("java:module/FaqFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DoChangeFaqCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
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
        
        String faqId = request.getParameter("faq_id");
        String name = request.getParameter("name");
        String answer = request.getParameter("answer");
        if(faqId != null && !faqId.isEmpty()){
            Faq editFaq = faqFacade.find(new Long(faqId));
            editFaq.setName(name);
            editFaq.setAnswer(answer);
            faqFacade.edit(editFaq);
        }else{
            request.setAttribute("info", "Возможно не указан вопрос для изменения!");
        }
        
        request.setAttribute("faq", faqFacade.findAll());
        
        return RoutingManager.getRoute("path.page.allFaq");
    }
}