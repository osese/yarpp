package com.osese.Yarpp.Receipt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {
	
	@Autowired
	ReceiptRepo receiptRepo;
	
	public Receipt save(Receipt receipt) {
		return receiptRepo.save(receipt);
	}
	public Iterable<Receipt> findAll(){
		return receiptRepo.findAll();
	}
	
	public Receipt findById(Long id) {
		return receiptRepo.findById(id).get();
	}
	
	public void deleteById(Long id) {
		receiptRepo.deleteById(id);
	}
	public Iterable<Receipt> findFromTo(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return receiptRepo.findFromTo(startDate, endDate);
	}
	
	
	
}
