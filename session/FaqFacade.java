package session;

import entity.Faq;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FaqFacade extends AbstractFacade<Faq> {

    @PersistenceContext(unitName = "KropachevShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaqFacade() {
        super(Faq.class);
    }
    
    public Faq find(String name){
        try {
            return (Faq) em.createQuery("SELECT u FROM Faq u WHERE u.name=:name")
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
    
}
