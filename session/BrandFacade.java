package session;

import entity.Brand;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BrandFacade extends AbstractFacade<Brand> {

    @PersistenceContext(unitName = "KropachevShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BrandFacade() {
        super(Brand.class);
    }
    
    public Brand find(String name){
        try {
            return (Brand) em.createQuery("SELECT u FROM Brand u WHERE u.name=:name")
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
}
