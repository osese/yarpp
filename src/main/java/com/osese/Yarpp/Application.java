package com.osese.Yarpp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.osese.Yarpp.Category.Category;
import com.osese.Yarpp.Category.CategoryRepo;
import com.osese.Yarpp.Desk.Desk;
import com.osese.Yarpp.Desk.DeskRepo;
import com.osese.Yarpp.DeskPosition.DeskPosition;
import com.osese.Yarpp.DeskPosition.DeskPositionRepo;
import com.osese.Yarpp.Product.Product;
import com.osese.Yarpp.Product.ProductRepo;
import com.osese.Yarpp.RestoUser.RestoUser;
import com.osese.Yarpp.RestoUser.RestoUserRepo;
import com.osese.Yarpp.UserRole.UserRoleRepo;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	DeskRepo deskRepo;
	@Autowired 
	DeskPositionRepo deskPosRepo;
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	RestoUserRepo userRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	UserRoleRepo userRoleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		if(!userRepo.findAll().iterator().hasNext()) {
			
			DeskPosition dp2 = new DeskPosition();
			dp2.setName("MASA YERI");
			
			deskPosRepo.save(dp2);
			
			Desk d = new Desk();
			d.setName("MASA");
			d.setPosition(dp2);
			deskRepo.save(d);
			
			
			
			
			Category c2 = new Category();
			c2.setName("GENEL");
			
			categoryRepo.save(c2);
			
			
			Product p = new Product();
			p.setName("URUN");
			p.setPrice(0.50);
			p.setCategories(c2);
			productRepo.save(p);	
			
			RestoUser admin = new RestoUser();
			admin.setUsername("admin");
			admin.setName("ADMIN");
			admin.setPassword("1234");
			admin.setRoles(new String[] {"ROLE_ADMIN"});
			
			userRepo.save(admin);
		}
	}
	
}