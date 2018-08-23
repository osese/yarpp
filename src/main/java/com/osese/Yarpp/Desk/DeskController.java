package com.osese.Yarpp.Desk;


import java.util.Date;
import java.util.List;

import javax.print.PrintException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osese.Yarpp.DeskPosition.DeskPosition;
import com.osese.Yarpp.DeskPosition.DeskPositionRepo;
import com.osese.Yarpp.Order.Order;
import com.osese.Yarpp.Order.OrderRepo;
import com.osese.Yarpp.Order.OrderStatus;
import com.osese.Yarpp.OrderLine.OrderLine;
import com.osese.Yarpp.OrderLine.OrderLineRepo;
import com.osese.Yarpp.Payment.Payment;
import com.osese.Yarpp.Payment.PaymentRepo;
import com.osese.Yarpp.PrinterHelper.PrinterHelper;
import com.osese.Yarpp.Receipt.Receipt;
import com.osese.Yarpp.Receipt.ReceiptRepo;
import com.osese.Yarpp.RestoUser.RestoUser;
import com.osese.Yarpp.RestoUser.RestoUserRepo;
import com.osese.Yarpp.UserRole.UserRole;


@RestController
@RequestMapping("/desk")
public class DeskController {
	
	@Autowired
	DeskRepo deskRepo;
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	PaymentRepo paymentRepo;
	
	@Autowired 
	RestoUserRepo userRepo;
	
	@Autowired 
	ReceiptRepo receiptRepo;
	
	@Autowired
	OrderLineRepo receiptLineRepo;
	
	@Autowired
	DeskPositionRepo deskPositionRepo;
	
	@GetMapping
	public Iterable<Desk> getAll(){
		return deskRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Desk getOne(@PathVariable Integer id) {
		return deskRepo.findById(id).get();
	}
	
	
	@PostMapping
	public Desk addDesk(@RequestBody Desk desk) {
		
		DeskPosition deskPosition = desk.getPosition();
		
		System.out.println(deskPosition);
		
		if(deskPosition != null) {
			DeskPosition d2 = deskPositionRepo.findByName(deskPosition.getName());
			if(d2 == null) {
				deskPositionRepo.save(deskPosition);
			}else {
				desk.setPosition(d2);
			}
		}
		
		return deskRepo.save(desk);
	}
	
	
	
	@PatchMapping("/{id}")
	public void editDesk(@PathVariable Integer id, @RequestParam("name") String name) {
		deskRepo.setDeskNameForId(name, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteDesk(@PathVariable Integer id) {
		deskRepo.deleteById(id);
	}
	
	@PostMapping("/{id}/order")
	public Order addOrder(Authentication auth, @PathVariable Integer id, @RequestBody List<OrderLine> orderLines) { 
		RestoUser user = userRepo.findByUsername(auth.getName());

		Desk desk = deskRepo.findById(id).get();
		desk.setState(true);
		Order order = new Order();
		order.setDesk(desk);
		order.setUser(user);
		
		for(int i=0; i<orderLines.size(); i++) {
			orderLines.get(i).setOrder(order);
		}
		
		order.setOrderLines(orderLines);
		
		orderRepo.save(order);
		return order;
	}
	
	@PostMapping("/{id}/order/yazdir")
	public Order addAndPrintOrder(Authentication auth, @PathVariable Integer id, @RequestBody List<OrderLine> orderLines) {
		// Todo send it to printer (if printer option selected) then add it. 
		// Todo set order taker 
		RestoUser user = userRepo.findByUsername(auth.getName());
		
		
		Desk desk = deskRepo.findById(id).get();
		desk.setState(true);
		Order order = new Order();
		order.setDesk(desk);
		order.setUser(user);
		
		for(int i=0; i<orderLines.size(); i++) {
			orderLines.get(i).setOrder(order);
		}
		
		order.setOrderLines(orderLines);
		
		orderRepo.save(order);
		
		PrinterHelper printerHelper = new PrinterHelper();
		printerHelper.setPrinterForKitchen(order);
		printerHelper.printToConsole();
		
		try {
			printerHelper.printTo("MUTFAK");
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	
	@GetMapping("/{id}/order")
	public List<Order> getOrder(@PathVariable Integer id) {
		Desk desk = deskRepo.findById(id).get();
		
		return desk.getOrders();
	}
	
	@GetMapping("/{id}/payments")
	public List<Payment> getPayments(@PathVariable Integer id){
		Desk desk = deskRepo.findById(id).get();
		
		return desk.getPayments();
	}
	
	@PostMapping("/{id}/payments")
	public List<Payment> addPayment(@PathVariable Integer id, @RequestBody Payment payment){
		Desk desk = deskRepo.findById(id).get();
		payment.setDesk(desk);
		paymentRepo.save(payment);
		return desk.getPayments();
	}
	
	@PostMapping("/{id}/onay")
	public Receipt orderOnay(@PathVariable Integer id){
		Desk desk = deskRepo.findById(id).get();
		Receipt receipt = new Receipt();

		List<Order> orders = desk.getOrders();
		List<Payment> payments = desk.getPayments();
		
		receipt.setTableName(desk.getName());
		receipt.setEndDate(new Date());
		
		double total = 0;
		double payed = 0;
		
		
		for(Order order : orders) {
			order.setDesk(null);
			order.setOrderStatus(OrderStatus.CLOSED);
			for(OrderLine line : order.getOrderLines()) {
				total += line.getTotal();
			}
		}
			
		for(Payment payment : payments) {
			payment.setDesk(null);
			payed += payment.getAmount();
		}
		
		receipt.getOrders().addAll(orders);
		receipt.getPayments().addAll(payments);
		receipt.setTotal(total);
		receipt.setPayed(payed);
		desk.setState(false);
		
		
		receiptRepo.save(receipt);
		deskRepo.save(desk);
		return receipt;
	}
	
	@PostMapping("/{id}/yazdir")
	public Receipt orderOnayVeYazdir(@PathVariable Integer id){
		Desk desk = deskRepo.findById(id).get();
		Receipt receipt = new Receipt();

		List<Order> orders = desk.getOrders();
		List<Payment> payments = desk.getPayments();
		
		receipt.setTableName(desk.getName());
		receipt.setEndDate(new Date());
		
		double total = 0;
		double payed = 0;
		
		
		for(Order order : orders) {
			order.setOrderStatus(OrderStatus.CLOSED);
			for(OrderLine line : order.getOrderLines()) {
				total += line.getTotal();
			}
		}
			
		for(Payment payment : payments) {
			payed += payment.getAmount();
		}
		
		receipt.getOrders().addAll(orders);
		receipt.getPayments().addAll(payments);
		receipt.setTotal(total);
		receipt.setPayed(payed);
		
		
		PrinterHelper printerHelper = new PrinterHelper();
		printerHelper.setPrinterForKasa(receipt);
		printerHelper.printToConsole();
		
		try {
			printerHelper.printTo("KASA");
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return receipt;
	}
	
	
	@PostMapping("/{srcId}/tasi/{destId}")
	public void masaTasi(@PathVariable Integer srcId, @PathVariable Integer destId) {
		Desk src = deskRepo.findById(srcId).get();
			
		Desk dest = deskRepo.findById(destId).get();
		
		if(dest.getState() == false) {
			dest.setState(true);
		}

		src.getPayments().forEach(payment -> {
			payment.setDesk(dest);
		});
		
		src.getOrders().forEach(order -> {
			order.setDesk(dest);
		});
		
		src.clear();
		deskRepo.save(src);
		deskRepo.save(dest);
	}
	
	
}
