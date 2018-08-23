package com.osese.Yarpp.OrderLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderline")
public class OrderLineController {

	@Autowired
	OrderLineRepo orderLineRepo;
	
	@GetMapping
	public Iterable<OrderLine> getAll(){
		return orderLineRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public OrderLine getOne(@PathVariable Long id) {
		return orderLineRepo.findById(id).get();
	}
	
	@PostMapping
	public OrderLine addReceiptLine(@RequestBody OrderLine receiptLine) {
		return orderLineRepo.save(receiptLine);
	}

	@DeleteMapping("/{id}")
	public void deleteReceiptLine(@PathVariable Long id) {
		
		orderLineRepo.deleteById(id);
	}
	
}
