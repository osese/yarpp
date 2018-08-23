package com.osese.Yarpp.Desk;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource(collectionResourceRel = "desks", path="desks")
public interface DeskRepo extends CrudRepository<Desk, Integer>{
	Desk findFirstByName(@Param(value = "name") String name);
	
	@Modifying
	@Transactional
	@Query("update Desk d set d.name = ?1 where d.id = ?2")
	void setDeskNameForId(String name, Integer id);
	

}
