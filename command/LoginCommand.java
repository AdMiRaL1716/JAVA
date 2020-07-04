package command;

import command.creater.RoutingManager;
import interfaces.ActionCommand;
import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("info", "Эта строка создана классом LoginCommand");
        return RoutingManager.getRoute("path.page.login");
    }
    
}
