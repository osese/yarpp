package com.osese.Yarpp.Order;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osese.Yarpp.OrderLine.OrderLine;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderRepo orderRepo;
	
	
	@GetMapping
	public Iterable<Order> getAll(){
		return orderRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Order getOne(@PathVariable Long id){
		return orderRepo.findById(id).get();
	}
	
	@PostMapping("/{id}/done")
	public Order changeStatus(@PathVariable Long id) {
		Order managed = orderRepo.findById(id).get();
		managed.setOrderStatus(OrderStatus.DONE);
		managed.setFinishDate(new Date());
		orderRepo.save(managed);
		return managed;
	}
	
	
	@PostMapping("/{id}/orderlines/")
	public Order changeOrderLines(@PathVariable Long id, @RequestParam List<OrderLine> orderLines) {
		Order managed = orderRepo.findById(id).get();
	
		managed.getOrderLines().clear();
		managed.getOrderLines().addAll(orderLines);
		
		return managed;	
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderRepo.deleteById(id);
	}
	
}
