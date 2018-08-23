package com.osese.Yarpp.Order;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface OrderRepo extends CrudRepository<Order, Long>{
	@Override
	Order save(@Param("order") Order order);
}
