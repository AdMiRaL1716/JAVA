package command;


import classes.AccessUser;
import classes.RoleUser;
import classes.Roles;
import classes.UserService;
import command.creater.RoutingManager;
import entity.User;
import interfaces.ActionCommand;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import security.EncriptPass;
import session.UserFacade;

public class EmptyCommand implements ActionCommand{
    private UserFacade  userFacade;

    public EmptyCommand() {
        try {
            Context context;
            context = new InitialContext();
            userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(EmptyCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
        }
    }
    

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userFacade.findAll();
        if (users.isEmpty()){
            EncriptPass encriptPass = new EncriptPass();
            String salts = encriptPass.getSalts();
            String pass = encriptPass.getEncriptString(salts+"12345");
            User newUser = new User("Admin", "Administrator", pass, "Admin", "cawa4444@mail.ru", "53835970", salts);
            userFacade.create(newUser);
            UserService chr = new UserService();
            chr.setRole(newUser, Roles.ADMIN);
            
        }
								AccessUser au = new AccessUser(); 
        User regUser = au.onAccess(request); 
        RoleUser roleUser = new RoleUser(); 
        String role = roleUser.getRole(regUser); 
        request.setAttribute("role", role);
								
        request.setAttribute("info", "Hello from Controller");
        return RoutingManager.getRoute("path.page.empty");
    }
    
}
