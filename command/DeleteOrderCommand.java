package command;

import classes.AccessUser;
import classes.RoleUser;
import classes.Roles;
import classes.UserService;
import command.creater.RoutingManager;
import entity.Message;
import entity.Orders;
import entity.User;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.MessageFacade;
import session.OrdersFacade;

public class DeleteOrderCommand implements ActionCommand {

    private OrdersFacade ordersFacade;

    public DeleteOrderCommand() {
        try {
            Context context;
            context = new InitialContext();
            ordersFacade = (OrdersFacade) context.lookup("java:module/OrdersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DeleteOrderCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
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
            Orders deleteOrder = ordersFacade.find(new Long(id));
            ordersFacade.remove(deleteOrder);
            request.setAttribute("info", "Заказ удален успешно");
        }catch(Exception e){
            request.setAttribute("info", "Заказ не удалось удалить");
        }
        
        request.setAttribute("allOrders", ordersFacade.findAll());
        
        return RoutingManager.getRoute("path.page.allOrder");
    }
}