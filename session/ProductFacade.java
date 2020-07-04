package session;

import entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "KropachevShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public Product find(String name){
        try {
            return (Product) em.createQuery("SELECT u FROM Product u WHERE u.name=:name")
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }

	public List<Product> findAllOrdered(){
		try {
           return (List<Product>)em.createQuery("SELECT u FROM Product u ORDER BY u.name ASC")
																			.getResultList();
        }
        catch (Exception e){
            return null;
        }
	}

    
}
