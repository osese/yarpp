package com.osese.Yarpp.Receipt;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	
	@Autowired
	ReceiptService receiptService;
	
	@GetMapping
	public Iterable<Receipt> getAll(){
		return receiptService.findAll();
	}
	
	// find last 5 receipt vice verse 
	
	
	@GetMapping("/{id}")
	public Receipt getOne(@PathVariable Long id) {
		return receiptService.findById(id);
	}
	
	@PostMapping
	public Receipt save(@RequestBody Receipt receipt) {
		return receiptService.save(receipt);
	}
	
	@GetMapping("/date/")
	public Iterable<Receipt> getFromTo(@RequestParam String start, @RequestParam String end){
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("dd-MM-yyyy HH:mm:ss");
		try {
			Date startDate = sdf.parse(start);
			Date endDate = sdf.parse(end);
			
			return receiptService.findFromTo(startDate, endDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return receiptService.findAll();
	}
}
