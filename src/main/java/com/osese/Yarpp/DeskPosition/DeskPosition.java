package com.osese.Yarpp.DeskPosition;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osese.Yarpp.Desk.Desk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author revolver
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DeskPosition {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(unique=true)
    String name;
    
    @OneToMany(mappedBy = "position")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private Collection<Desk> tables = new ArrayList<>();

    void addTable(Desk b1) {
        b1.setPosition(this);
        tables.add(b1);
    }

    
}
