package command;

import classes.Roles;
import classes.UserService;
import command.creater.RoutingManager;
import entity.User;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import security.EncriptPass;
import session.UserFacade;

public class EnterCommand implements ActionCommand{
    private UserFacade  userFacade;

    public EnterCommand() {
        try {
            Context context;
            context = new InitialContext();
            userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(EnterCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
        }
    }
    
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User regUser = userFacade.find(login);
        if(regUser==null) {
            request.setAttribute("info", "Такой логин или пароль не существует!");
            return RoutingManager.getRoute("path.page.login");
        }
        EncriptPass encriptPass = new EncriptPass();
        String salts = regUser.getSalts();
        password = salts + password;
        password = encriptPass.getEncriptString(password);
        if(password == null){
            throw new Error("Возникла ошибка, обратитесь к разработчику!");
        }
        if(!password.equals(regUser.getPassword())) {
            request.setAttribute("info", "Такой логин или пароль не существует!");
            return RoutingManager.getRoute("path.page.login"); 
        }
        
        HttpSession session = request.getSession(true);
        session.setAttribute("regUser", regUser);
        
        
        UserService us = new UserService();
        if(us.userContainRole(Roles.ADMIN.toString(), regUser)){
            String page = RoutingManager.getRoute("path.page.admin");
            return page;
        }
        if(us.userContainRole(Roles.MANAGER.toString(), regUser)){
            String page = RoutingManager.getRoute("path.page.manager");
            return page;
        } 
        if(us.userContainRole(Roles.SUPPORT.toString(), regUser)){
            String page = RoutingManager.getRoute("path.page.support");
            return page;
        }

        return RoutingManager.getRoute("path.page.user");
    }
    
}
