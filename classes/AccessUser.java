package classes;

import entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Класс служит для проверки, выполнил пользователь вход в систему или нет.
 *
 */
public class AccessUser {

    public User onAccess(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        User regUser = (User) session.getAttribute("regUser");
        if (regUser == null) {
            return null;
        }
        return regUser;
    }
}
