/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Orders;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders>{

	@PersistenceContext(unitName="KropachevShopPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager(){
		return em;
	}

	public OrdersFacade(){
		super(Orders.class);
	}
	
	public Orders find(String name){
        try {
            return (Orders) em.createQuery("SELECT u FROM Orders u WHERE u.name=:name")
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
	
}
