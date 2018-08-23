package com.osese.Yarpp.Category;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osese.Yarpp.Product.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column( unique = true)
    private String name;
    
    @Lob
    private byte[] image;  
    
    @OneToMany(mappedBy="categories")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private Collection<Product> products = new HashSet<>();
    
    
    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent; 
    
}

