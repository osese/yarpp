package com.osese.Yarpp.RestoUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RestoUserController {
	
	@Autowired 
	RestoUserRepo userRepo;
	
	/*
	@Autowired
	RestoUserService userService;
	*/
	
	@GetMapping
	public Iterable<RestoUser> getAll(){
		return userRepo.findAll();
	} 
	
	@GetMapping("/{id}")
	public RestoUser getOne(@PathVariable Long id) {
		return userRepo.findById(id).get();
	}
	
	@PostMapping
	public RestoUser addUser(@RequestBody RestoUser user) {
		return userRepo.save(user);
	}
	
	@PatchMapping("/{id}")
	public void editUser(@PathVariable Long id, @RequestBody RestoUser user) {
		RestoUser current = userRepo.findById(id).get();
		current.setName(user.getName());
		current.setUsername(user.getUsername());
		current.setPassword(user.getPassword());
		userRepo.save(current);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userRepo.deleteById(id);
	}
	
	
	@GetMapping("/auth")
	public RestoUser getAuthUser(Authentication auth) {
		auth.getName();
		return userRepo.findByUsername(auth.getName());
	} 
}
