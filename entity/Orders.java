/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author pupil
 */
@Entity
public class Orders{
	   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
				@OneToOne
				private Product product;
				private String name;
				private String lastname;
				private String email;
				private String adress;
				private String telefon;

	public Orders(){
	}

	public Orders(Product product,String name,String lastname,String email,String adress,String telefon){
		this.product=product;
		this.name=name;
		this.lastname=lastname;
		this.email=email;
		this.adress=adress;
		this.telefon=telefon;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Product getProduct(){
		return product;
	}

	public void setProduct(Product product){
		this.product=product;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getLastname(){
		return lastname;
	}

	public void setLastname(String lastname){
		this.lastname=lastname;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getAdress(){
		return adress;
	}

	public void setAdress(String adress){
		this.adress=adress;
	}

	public String getTelefon(){
		return telefon;
	}

	public void setTelefon(String telefon){
		this.telefon=telefon;
	}

	@Override
	public int hashCode(){
		int hash=7;
		hash=53*hash+Objects.hashCode(this.id);
		hash=53*hash+Objects.hashCode(this.product);
		hash=53*hash+Objects.hashCode(this.name);
		hash=53*hash+Objects.hashCode(this.lastname);
		hash=53*hash+Objects.hashCode(this.email);
		hash=53*hash+Objects.hashCode(this.adress);
		hash=53*hash+Objects.hashCode(this.telefon);
		return hash;
	}

	@Override
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(obj==null){
			return false;
		}
		if(getClass()!=obj.getClass()){
			return false;
		}
		final Orders other=(Orders)obj;
		if(!Objects.equals(this.name,other.name)){
			return false;
		}
		if(!Objects.equals(this.lastname,other.lastname)){
			return false;
		}
		if(!Objects.equals(this.email,other.email)){
			return false;
		}
		if(!Objects.equals(this.adress,other.adress)){
			return false;
		}
		if(!Objects.equals(this.telefon,other.telefon)){
			return false;
		}
		if(!Objects.equals(this.id,other.id)){
			return false;
		}
		if(!Objects.equals(this.product,other.product)){
			return false;
		}
		return true;
	}

	@Override
	public String toString(){
		return "Order{"+"id="+id+", product="+product.getName()+", name="+name+", lastname="+lastname+", email="+email+", adress="+adress+", telefon="+telefon+'}';
	}
				
				
	
}
