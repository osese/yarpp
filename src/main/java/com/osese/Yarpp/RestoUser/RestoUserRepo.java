package com.osese.Yarpp.RestoUser;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource(exported = false)
public interface RestoUserRepo extends CrudRepository<RestoUser, Long>{

	RestoUser findByName(String name);
	RestoUser findByUsername(String name);
}
