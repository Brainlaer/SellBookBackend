package com.analitrix.sellbook.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long user;
	@Column(name = "expedition_date")
	private Date expeditionDate;
	@Column(name = "total_cost")
	private double totalCost;
	private String tracking;

	
	@PrePersist
	public void prePersist() {
		expedite();
	}
	
	public Date expedite() {
		return this.expeditionDate=new Date();
	}
}
