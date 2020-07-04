package session;

import entity.User;
import entity.UserRole;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRoleFacade extends AbstractFacade<UserRole> {

    @PersistenceContext(unitName = "KropachevShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRoleFacade() {
        super(UserRole.class);
    }
    
    public List<UserRole> findRoles (User user){
        try{
            List<UserRole> rolles = em.createQuery("SELECT ur FROM UserRole ur WHERE ur.user=:user")
                    .setParameter("user", user)
                    .getResultList();
            return rolles;
        }
        catch (Exception e){
            return null;
        }
    } 
}
