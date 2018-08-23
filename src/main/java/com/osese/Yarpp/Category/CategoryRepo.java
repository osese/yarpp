package com.osese.Yarpp.Category;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:3000")
@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer>{
	@Modifying
	@Transactional
	@Query("update Category c set c.name = ?1 where c.id = ?2")
	void setCategoryNameForId(String name, Integer id);
	

}
