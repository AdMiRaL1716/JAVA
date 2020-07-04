package command;

import classes.AccessUser;
import classes.RoleUser;
import command.creater.RoutingManager;
import entity.User;
import interfaces.ActionCommand;
import javax.servlet.http.HttpServletRequest;

public class ContactCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
					
					AccessUser au = new AccessUser(); 
        User regUser = au.onAccess(request); 
        RoleUser roleUser = new RoleUser(); 
        String role = roleUser.getRole(regUser); 
        request.setAttribute("role", role);
					
					
        request.setAttribute("info", "Эта строка создана классом ContactCommand");
        return RoutingManager.getRoute("path.page.contact");
    }
    
}
