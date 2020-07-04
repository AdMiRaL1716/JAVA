package session;

import entity.Message;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MessageFacade extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "KropachevShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
        public Message find(String name){
        try {
            return (Message) em.createQuery("SELECT u FROM Message u WHERE u.name=:name")
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
    
}
