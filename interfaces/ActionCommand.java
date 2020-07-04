package interfaces;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    public String execute(HttpServletRequest request);
}
