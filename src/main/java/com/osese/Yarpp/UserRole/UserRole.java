package com.osese.Yarpp.UserRole;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.osese.Yarpp.RestoUser.RestoUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRole {
	@Id 
	@GeneratedValue 
	private Long id;
	
	private String name;
	
	
}