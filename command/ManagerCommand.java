package command;

import classes.Roles;
import classes.UserService;
import command.creater.RoutingManager;
import entity.User;
import interfaces.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ManagerCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("info", "Эта строка создана классом UserCommand");
        HttpSession session = request.getSession(false);
        if (session == null){
           return RoutingManager.getRoute("path.page.login"); 
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null) {
            request.setAttribute("info", "Войдите!");
            return RoutingManager.getRoute("path.page.login");
        }
        
        UserService us = new UserService();
        if(!us.userContainRole(Roles.MANAGER.toString(), regUser)){
            String page = RoutingManager.getRoute("path.page.login");
            return page;
        }
        
        request.setAttribute("userName", regUser.getUserName());
        return RoutingManager.getRoute("path.page.manager");
    }
}
