package com.osese.Yarpp.Payment;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osese.Yarpp.Desk.Desk;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
	@Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
	
	@JsonFormat
    	(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date paymentDate = new Date();
	
	private Double amount;
	private PaymentType paymentType;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Desk desk; 
	
	
}
