package command;

import classes.Roles;
import classes.UserService;
import command.creater.RoutingManager;
import entity.User;
import interfaces.ActionCommand;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.UserFacade;

public class SetRoleCommand implements ActionCommand{
    private UserFacade userFacade;
    
    public SetRoleCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    @Override
    public String execute(HttpServletRequest request) {
        String selectUser = request.getParameter("selectUser");
        String selectRole = request.getParameter("selectRole");
        HttpSession session = request.getSession(false);
        if(session == null){
            String page = RoutingManager.getRoute("path.page.login");
            return page;
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null){
            String page = RoutingManager.getRoute("path.page.login");
            return page;
        }

        UserService us = new UserService();
        if(!us.userContainRole(Roles.ADMIN.toString(), regUser)){
            String page = RoutingManager.getRoute("path.page.login");
            return page;
        }
        
        if(selectRole == null || selectRole.isEmpty()){
            String page = RoutingManager.getRoute("path.page.login");
            return page;
        }
        
        User selUser = userFacade.find(new Long(selectUser));
        UserService changeRole = new UserService();
        changeRole.setRole(selUser, Roles.valueOf(selectRole));
        
        
        List<User> users = userFacade.findAll();
        Map<User,String>mapUsers=new HashMap<>();
        for (User user : users) {
            String role = us.getRole(user);
            mapUsers.put(user, role);
        }
        request.setAttribute("mapUsers", mapUsers);
        request.setAttribute("roles", Roles.values());
        String page = RoutingManager.getRoute("path.page.admin");
        return page;
        
        
    }
    
}
