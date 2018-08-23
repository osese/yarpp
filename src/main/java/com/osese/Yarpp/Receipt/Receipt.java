package com.osese.Yarpp.Receipt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.osese.Yarpp.Desk.Desk;
import com.osese.Yarpp.Order.Order;
import com.osese.Yarpp.OrderLine.OrderLine;
import com.osese.Yarpp.Payment.Payment;
import com.osese.Yarpp.RestoUser.RestoUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Receipt {
	@Id 
	@GeneratedValue 
	private Long id;
	
	
	private String userName; 
	private String tableName;
	
	@JsonFormat
		(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")	
	private Date endDate;
	
	private Double total;
	private Double payed;
	
	@ElementCollection
	private List<Order> orders = new ArrayList<>();
	
	
	@ElementCollection
	private List<Payment> payments = new ArrayList<>();
	
}
