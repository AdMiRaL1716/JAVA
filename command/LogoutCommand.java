package command;

import command.creater.RoutingManager;
import interfaces.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null){
           session.invalidate();
           request.setAttribute("info", "Вы успешно вышли");
        }
        return RoutingManager.getRoute("path.page.login");
    }
    
}
