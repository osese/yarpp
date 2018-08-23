package com.osese.Yarpp.OrderLine;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource(excerptProjection=OrderLineExcerpt.class)
public interface OrderLineRepo extends CrudRepository<OrderLine, Long>{
	
	void deleteById(Long id);
}
