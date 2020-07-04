package command;

import command.creater.RoutingManager;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import session.OrdersFacade;
import session.ProductFacade;

public class MakeOrderCommand implements ActionCommand{

	private ProductFacade productFacade;
	private OrdersFacade orderFacade;


    public MakeOrderCommand() {
        try {
            Context context;
            context = new InitialContext();
            productFacade = (ProductFacade) context.lookup("java:module/ProductFacade");
												orderFacade = (OrdersFacade) context.lookup("java:module/OrderFacade");
        } catch (NamingException ex) {
            Logger.getLogger(MakeOrderCommand.class.getName()).log(Level.SEVERE, "не удалось найти сесионный бин", ex);
        }
    }
	
	
	
    @Override
    public String execute(HttpServletRequest request) {
        String product_id = request.getParameter("product_id");
        if(product_id != null){
									return RoutingManager.getRoute("path.page.product");
								}
								request.setAttribute("product_id", product_id);
        return RoutingManager.getRoute("path.page.addToForm");
    }
    
}
