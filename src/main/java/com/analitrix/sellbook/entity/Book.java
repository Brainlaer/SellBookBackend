package com.analitrix.sellbook.entity;


import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	@Id
	@Column(unique = true)
	private Long isxn;
	private String title;
	@Column(name="date_posted")
	private Long datePosted;
	private boolean available;
	private Long units;
	private String editorial;
	private double cost;
	private String author;
	private String image;
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name="date_modified")
	private Date dateModified;
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
    @PrePersist
    public void prePersist() {
        modify();
        setAvailability();
    }
    
    public boolean setAvailability() {
        if(units>=1) {
        	return this.available=true;
        }else {
        	return this.available=false;
        }
    }
    
    public Long Sell() {
    	return this.units=this.units-1;
    }
    
    public Date modify() {
    	return this.dateModified = new Date();
    }
}
