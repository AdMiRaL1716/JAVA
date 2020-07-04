package classes;

import entity.User;
import entity.UserRole;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.UserRoleFacade;

public class UserService {
    private UserRoleFacade userRoleFacade;
    public UserService(){
        try{
            Context context;
            context = new InitialContext();
            this.userRoleFacade = (UserRoleFacade) context.lookup("java:module/UserRoleFacade");
        } 
        catch (NamingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Не удалось добавить бин", ex);
        }
    }
    public String getRole(User user){       
        List<UserRole> roles = userRoleFacade.findRoles(user);
        List<String> strRoles = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            UserRole ur = roles.get(i);
            strRoles.add(ur.toString());
        }
        if (strRoles.contains("ADMIN")){
            return "ADMIN";          
        }
        if (strRoles.contains("MANAGER")){
            return "MANAGER";          
        }
        if (strRoles.contains("SUPPORT")){
            return "SUPPORT";          
        }
        if (strRoles.contains("USER")){
            return "USER";          
        }
        return null;
    }
    public void setRole(User user, Roles roleName){
        List<UserRole> roles = userRoleFacade.findRoles(user);
        if (roles != null){
            for (int i = 0; i < roles.size(); i++){
                UserRole userRole = roles.get(i);
                userRoleFacade.remove(userRole);
            }
        }
        UserRole roleUser = new UserRole();
        if (null != roleName)
            switch (roleName.toString()){
                case "ADMIN":
                    roleUser.setUser(user);
                    roleUser.setRole(roleName.ADMIN.toString());
                    userRoleFacade.create(roleUser);
                    roleUser.setRole(roleName.MANAGER.toString());
                    userRoleFacade.create(roleUser);
                    roleUser.setRole(roleName.SUPPORT.toString());
                    userRoleFacade.create(roleUser);
                    roleUser.setRole(roleName.USER.toString());
                    userRoleFacade.create(roleUser);
                break;
                case "MANAGER":
                    roleUser.setUser(user);
                    roleUser.setRole(roleName.MANAGER.toString());
                    userRoleFacade.create(roleUser);
                    roleUser.setRole(roleName.USER.toString());
                    userRoleFacade.create(roleUser);
                break;
                case "SUPPORT":
                    roleUser.setUser(user);
                    roleUser.setRole(roleName.SUPPORT.toString());
                    userRoleFacade.create(roleUser);
                    roleUser.setRole(roleName.USER.toString());
                    userRoleFacade.create(roleUser);
                break;
                case "USER":
                    roleUser.setUser(user);
                    roleUser.setRole(roleName.USER.toString());
                    userRoleFacade.create(roleUser);
                break;
                
            }
        
    }
    public boolean userContainRole (String role, User user){
        List<UserRole> userRoles = userRoleFacade.findRoles(user);
        List<String> rolles = new ArrayList<>();
        for (int i = 0; i < userRoles.size(); i++) {
            UserRole userRole = userRoles.get(i);
            rolles.add(userRole.getRole());
        }
       if (rolles.contains(role)){
           return true;
       } else {
           return false;
       }
    }
}
