package com.osese.Yarpp.DeskPosition;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
public interface DeskPositionRepo extends CrudRepository<DeskPosition, Integer>{
	
	DeskPosition findByName(String name);
}
