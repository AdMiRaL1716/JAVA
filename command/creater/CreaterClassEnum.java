package command.creater;

import command.AddBrandCommand;
import command.AddFaqCommand;
import command.AddMessageCommand;
import command.BrandCommand;
import command.AddNewBrandCommand;
import command.AddNewFaqCommand;
import command.AddNewMessageCommand;
import command.AddNewOrderCommand;
import command.AdminCommand;
import command.PanelAdminCommand;
import command.EmptyCommand;
import command.EnterCommand;
import command.LoginCommand;
import command.LogoutCommand;
import command.NewUserCommand;
import command.AddNewProductCommand;
import command.AddProductCommand;
import command.AddToFormCommand;
import command.AllBrandCommand;
import command.AllFaqCommand;
import command.AllMessageCommand;
import command.AllOrderCommand;
import command.AllProductCommand;
import command.ContactCommand;
import command.DeleteBrandCommand;
import command.DeleteFaqCommand;
import command.DeleteMessageCommand;
import command.DeleteOrderCommand;
import command.DeleteProductCommand;
import command.DoChangeBrandCommand;
import command.DoChangeFaqCommand;
import command.DoChangeProductCommand;
import command.EditBrandCommand;
import command.EditFaqCommand;
import command.EditProductCommand;
import command.FaqCommand;
import command.MakeOrderCommand;
import command.ManagerCommand;
import command.MessageCompleteCommand;
import command.OrderComlpeteCommand;
import command.ProductCommand;
import command.RegistrationCommand;
import command.SetRoleCommand;
import command.SupportCommand;
import command.UserCommand;
import interfaces.ActionCommand;


public enum CreaterClassEnum {
EMPTY{{this.command = new EmptyCommand();}},
LOGIN{{this.command = new LoginCommand();}},
ENTER{{this.command = new EnterCommand();}},
REGISTRATION{{this.command = new RegistrationCommand();}},
USER{{this.command = new UserCommand();}},
ADMIN{{this.command = new AdminCommand();}},
MANAGER{{this.command = new ManagerCommand();}},
SUPPORT{{this.command = new SupportCommand();}},
SETROLE{{this.command = new SetRoleCommand();}},
ADDNEWBRAND{{this.command = new AddNewBrandCommand();}},
ADDBRAND{{this.command = new AddBrandCommand();}},
ADDFAQ{{this.command = new AddFaqCommand();}},
DELETEBRAND{{this.command = new DeleteBrandCommand();}},
DELETEMESSAGE{{this.command = new DeleteMessageCommand();}},
DELETEPRODUCT{{this.command = new DeleteProductCommand();}},
DELETEORDER{{this.command = new DeleteOrderCommand();}},
DELETEFAQ{{this.command = new DeleteFaqCommand();}},
DOCHANGEBRAND{{this.command = new DoChangeBrandCommand();}},
DOCHANGEPRODUCT{{this.command = new DoChangeProductCommand();}},
DOCHANGEFAQ{{this.command = new DoChangeFaqCommand();}},
EDITBRAND{{this.command = new EditBrandCommand();}},
EDITPRODUCT{{this.command = new EditProductCommand();}},
EDITFAQ{{this.command = new EditFaqCommand();}},
BRAND{{this.command = new BrandCommand();}},
PRODUCT{{this.command = new ProductCommand();}},
CONTACT{{this.command = new ContactCommand();}},
ALLBRAND{{this.command = new AllBrandCommand();}},
ALLMESSAGE{{this.command = new AllMessageCommand();}},
ALLPRODUCT{{this.command = new AllProductCommand();}},
ALLORDER{{this.command = new AllOrderCommand();}},
ALLFAQ{{this.command = new AllFaqCommand();}},
FAQ{{this.command = new FaqCommand();}},
ORDERCOMLETE{{this.command = new OrderComlpeteCommand();}},
ADDMESSAGE{{this.command = new AddMessageCommand();}},
ADDPRODUCT{{this.command = new AddProductCommand();}},
ADDTOFORM{{this.command = new AddToFormCommand();}},
MAKEORDER{{this.command = new MakeOrderCommand();}},
ADDNEWMESSAGE{{this.command = new AddNewMessageCommand();}},
ADDNEWPRODUCT{{this.command = new AddNewProductCommand();}},
ADDNEWFAQ{{this.command = new AddNewFaqCommand();}},
ADDNEWORDER{{this.command = new AddNewOrderCommand();}},
MESSAGECOMPLETE{{this.command = new MessageCompleteCommand();}},
PANELADMIN{{this.command = new PanelAdminCommand();}},
NEWUSER{{this.command = new NewUserCommand();}},
LOGOUT{{this.command = new LogoutCommand();}};
    
    
    
 ActionCommand command;
    public ActionCommand getActionCommand(){
        return this.command;
    }
}
