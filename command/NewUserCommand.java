package command;

import classes.Roles;
import command.creater.RoutingManager;
import entity.User;
import entity.UserRole;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import security.EncriptPass;
import session.UserFacade;
import session.UserRoleFacade;

public class NewUserCommand implements ActionCommand{
    private UserFacade  userFacade;
    private UserRoleFacade  userRoleFacade;

    public NewUserCommand() {
        try {
            Context context;
            context = new InitialContext();
            userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            userRoleFacade = (UserRoleFacade) context.lookup("java:module/UserRoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(NewUserCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
        }
    }
        
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("info", "Эта строка создана классом NewUserCommand");
        String userName = request.getParameter("username");
        String login = request.getParameter("login");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String telefon = request.getParameter("telefon");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if(!password1.equals(password2)) {
            request.setAttribute("info", "Пароли не совпадают!");
            request.setAttribute("username", userName);
            request.setAttribute("login", login);
            return RoutingManager.getRoute("path.page.registration");
        }
        EncriptPass encriptPass = new EncriptPass();
        String salts = encriptPass.getSalts();
        String password = salts + password1;
        password = encriptPass.getEncriptString(password);
        if(password == null){
            throw new Error("Возникла ошибка, обратитесь к разработчику!");
        }
        
        User newUser = new User(userName, login, password, lastName, email, telefon, salts);
        userFacade.create(newUser);
        
        UserRole userRole = new UserRole(newUser, Roles.USER.toString(), true);
        userRoleFacade.create(userRole);
        return RoutingManager.getRoute("path.page.login");
    }
    
}
