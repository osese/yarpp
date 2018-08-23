package com.osese.Yarpp.Desk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.osese.Yarpp.DeskPosition.DeskPosition;
import com.osese.Yarpp.Order.Order;
import com.osese.Yarpp.OrderLine.OrderLine;
import com.osese.Yarpp.Payment.Payment;
import com.osese.Yarpp.RestoUser.RestoUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/*
 * TODO 
 * 1. Türkçe karakterlerden ıİşŞ desteklenmiyor. 
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Desk {
	
	
    @Id	
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(unique = true)
    private String name;
    
    private Boolean state = Boolean.FALSE;
    
    @ManyToOne
    @JoinColumn
    private DeskPosition position;
    
    @OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="desk")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
    
    public void clear() {
    	state = false;
    }
    
    @OneToMany(mappedBy="desk")
    @JsonIgnore
    private List<Payment> payments = new ArrayList<>();
    
    
    
}
