package com.osese.Yarpp.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osese.Yarpp.Desk.Desk;
import com.osese.Yarpp.OrderLine.OrderLine;
import com.osese.Yarpp.RestoUser.RestoUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ORDERS")
public class Order {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="ONUMBER")
	private Integer number;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private Collection<OrderLine> orderLines = new ArrayList<>();
	
	@Enumerated(EnumType.ORDINAL)
	private OrderStatus orderStatus = OrderStatus.TAKEN;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
    private RestoUser user;
	
	@ManyToOne
	@JsonIgnore
	private Desk desk;
	
	@JsonFormat
		(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date startDate = new Date();
	
	private Date finishDate = null;
}
