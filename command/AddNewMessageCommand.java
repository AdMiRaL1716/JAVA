package command;


import command.creater.RoutingManager;
import entity.Message;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import session.MessageFacade;


public class AddNewMessageCommand implements ActionCommand {

    private MessageFacade messageFacade;

    public AddNewMessageCommand() {
        try {
            Context context;
            context = new InitialContext();
            messageFacade = (MessageFacade) context.lookup("java:module/MessageFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AddNewMessageCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        Message newMessage = new Message(userName, email);
        try {
            messageFacade.create(newMessage);
            request.setAttribute("info", "Сообщение отправлено");
        }
        catch(Exception e){
            request.setAttribute("info", "Не удалось отправить сообщение");
        }
        return RoutingManager.getRoute("path.page.messageComplete");
    }
}