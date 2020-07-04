package command;

import classes.AccessUser;
import classes.RoleUser;
import command.creater.RoutingManager;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import entity.Product;
import entity.User;
import session.ProductFacade;
import javax.servlet.http.HttpServletRequest;

public class AddToFormCommand implements ActionCommand{

	private ProductFacade productFacade;

	public AddToFormCommand(){
		try{
			Context context;
			context=new InitialContext();
			productFacade=(ProductFacade)context.lookup("java:module/ProductFacade");
		}catch(NamingException ex){
			Logger.getLogger(AddToFormCommand.class.getName()).log(Level.SEVERE,"Не удалось найти бин",ex);
		}
	}

	@Override
	public String execute(HttpServletRequest request){
		
		AccessUser au = new AccessUser(); 
        User regUser = au.onAccess(request); 
        RoleUser roleUser = new RoleUser(); 
        String role = roleUser.getRole(regUser); 
        request.setAttribute("role", role);
		
		String product_id=request.getParameter("product_id");
		if(product_id==null){
			return RoutingManager.getRoute("path.page.products");
		}
		request.setAttribute("product_id",product_id);
		Product findProduct=productFacade.find(new Long(product_id));
		if(findProduct!=null){
			request.setAttribute("product",findProduct);
			return RoutingManager.getRoute("path.page.addToForm");
		}
		return RoutingManager.getRoute("path.page.products");
	}

}
