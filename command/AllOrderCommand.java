package command;

import classes.AccessUser;
import classes.RoleUser;
import classes.Roles;
import classes.UserService;
import command.creater.RoutingManager;
import entity.Orders;
import entity.User;
import interfaces.ActionCommand;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.OrdersFacade;

public class AllOrderCommand implements ActionCommand {

    private OrdersFacade ordersFacade;

    public AllOrderCommand() {
        try {
            Context context;
            context = new InitialContext();
            ordersFacade = (OrdersFacade) context.lookup("java:module/OrdersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AllOrderCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
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
        List<Orders> allOrders = ordersFacade.findAll();
        request.setAttribute("allOrders", allOrders);

        
        return RoutingManager.getRoute("path.page.allOrder");
    }
}