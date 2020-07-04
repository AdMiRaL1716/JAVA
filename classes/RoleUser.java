
package classes;

import entity.User;
import entity.UserRole;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.UserFacade;
import session.UserRoleFacade;


public class RoleUser {
    private UserFacade userFacade;
    private UserRoleFacade roleFacade;
    private User user;

    public RoleUser() {
        Context context;
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFacade = (UserRoleFacade) context.lookup("java:module/UserRoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(RoleUser.class.getName()).log(Level.INFO, "Не удалось найти сессионый бин", ex);
        }
    }

    public String getRole(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        User regUser = (User) session.getAttribute("regUser");
        if (regUser == null) {
            return null;
        }
        return getRole(regUser);
    }

    public String getRole(User user) {
        if (user == null) {
            return null;
        }
        for (Roles roleEnum : Roles.values()) {
            if (this.contains(roleEnum.toString(), user)) {
                return roleEnum.toString();
            }
        }
        return null;
    }

    public String getRole(String id) {
        if (id == null) {
            return null;
        }
        if ("".equals(id)) {
            return null;
        }
        try {
            user = userFacade.find(new Long(id));
            return getRole(user);
        } catch (NumberFormatException e) {
            Logger.getLogger(UserRole.class.getName()).log(Level.INFO, "Не удалось найти роль", e);
            return null;
        }
    }

    public boolean contains(String role, User user) {

        List<UserRole> rolles = roleFacade.findRoles(user);
        if (rolles == null) {
            return false;
        }
        for (int i = 0; i < rolles.size(); i++) {
            UserRole get = rolles.get(i);
            if (role.equals(get.getRole())) {
                return true;
            }
        }
        return false;
    }
}
