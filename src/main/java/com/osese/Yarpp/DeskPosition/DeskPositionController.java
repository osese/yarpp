package com.osese.Yarpp.DeskPosition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deskposition")
public class DeskPositionController {
	
	@Autowired
	DeskPositionRepo deskPositionRepo;
	
	@GetMapping 
	public Iterable<DeskPosition> getAll(){
		return deskPositionRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public DeskPosition getOne(@PathVariable Integer id) {
		return deskPositionRepo.findById(id).get();
	}
	
	@PostMapping
	public DeskPosition addDeskPosition(@RequestBody DeskPosition deskPosition) {
		return deskPositionRepo.save(deskPosition);
	}
	
	@DeleteMapping("/{id}")
	public String deleteDeskPosition(@PathVariable Integer id) {
		if(deskPositionRepo.findById(id).get().getTables().isEmpty()) {
			deskPositionRepo.deleteById(id);
			return "okey";
		}
		return "fail";
	}
}

	