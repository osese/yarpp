package com.osese.Yarpp.Product;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource
public interface ProductRepo extends CrudRepository<Product, Integer>{

	@Modifying
	@Transactional
	@Query("update Product p set p.code = ?2 , p.image= ?3, p.name = ?4, p.price = ?5 where p.id = ?1")
	void updateProduct(Integer id, String code, byte[] image, String name, Double price);
	
	@Override
	@Query("select p from Product p ")
	@EntityGraph(attributePaths= {"categories"})
	List<Product> findAll();
}
