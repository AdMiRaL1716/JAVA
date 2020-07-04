package command;

import classes.AccessUser;
import classes.RoleUser;
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
import session.BrandFacade;

public class AllBrandCommand implements ActionCommand {

    private BrandFacade brandFacade;

    public AllBrandCommand() {
        try {
            Context context;
            context = new InitialContext();
            brandFacade = (BrandFacade) context.lookup("java:module/BrandFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AllBrandCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
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
        if(!us.userContainRole(Roles.MANAGER.toString(), regUser)){
            String page = RoutingManager.getRoute("path.page.login");
            return page;
        }
        
        request.setAttribute("brands", brandFacade.findAll());

        
        return RoutingManager.getRoute("path.page.allBrand");
    }
}