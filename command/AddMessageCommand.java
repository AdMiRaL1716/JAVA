package command;

import command.creater.RoutingManager;
import interfaces.ActionCommand;
import javax.servlet.http.HttpServletRequest;

public class AddMessageCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("info", "Эта строка создана классом RegistrationCommand");
        return RoutingManager.getRoute("path.page.contact");
    }
    
}
