package com.osese.Yarpp.OrderLine;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osese.Yarpp.Order.Order;
import com.osese.Yarpp.RestoUser.RestoUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author revolver
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderLine implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8108292381874503368L;
	
	@Id
    @GeneratedValue
    private Long id;
    
    private Integer count;
    private String productName;
    private Double productPrice;
    
    @Formula("count * product_price")
    private Double total;
    
    private String note;
    
    @ManyToOne
    @JsonIgnore
    private Order order;
    	
    @PreRemove
    public void removeOrderLine() {
    	order.getOrderLines().remove(this);
    }
}
