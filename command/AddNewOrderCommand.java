package command;

import command.creater.RoutingManager;
import entity.Orders;
import entity.Product;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import session.OrdersFacade;
import session.ProductFacade;

public class AddNewOrderCommand implements ActionCommand{

	private ProductFacade productFacade;
	private OrdersFacade ordersFacade;


    public AddNewOrderCommand() {
        try {
            Context context;
            context = new InitialContext();
            productFacade = (ProductFacade) context.lookup("java:module/ProductFacade");
												ordersFacade = (OrdersFacade) context.lookup("java:module/OrdersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AddNewOrderCommand.class.getName()).log(Level.SEVERE, "не удалось найти сесионный бин", ex);
        }
    }
	
	
	
    @Override
    public String execute(HttpServletRequest request) {
        String product_id = request.getParameter("product_id");
								String name = request.getParameter("name");
								String lastname = request.getParameter("lastname");
								String email = request.getParameter("email");
								String telefon = request.getParameter("telefon");
								String adress = request.getParameter("adress");
								
								
        if(product_id != null){
									Product findProduct = productFacade.find(new Long(product_id));
            if(findProduct == null){
                return RoutingManager.getRoute("path.page.products");
            }
								Orders newOrder = new Orders(findProduct,name,lastname,email,adress,telefon);
								ordersFacade.create(newOrder);
								findProduct.setQuantity(findProduct.getQuantity()-1);
								productFacade.edit(findProduct);
            return RoutingManager.getRoute("path.page.orderComplete");
        }
    
								return RoutingManager.getRoute("path.page.orderComplete");
				}
}
