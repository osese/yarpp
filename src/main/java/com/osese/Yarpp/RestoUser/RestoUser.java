package com.osese.Yarpp.RestoUser;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.osese.Yarpp.UserRole.UserRole;

import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RestoUser {
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private Boolean isOnline = false;
    private byte[] image;
    // User preupdate to update this field;
    private Date lastLogin;
    
    @JsonProperty(access=Access.WRITE_ONLY)
    private String password; 
    
    private String[] roles;
    
    
    public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
    }
}