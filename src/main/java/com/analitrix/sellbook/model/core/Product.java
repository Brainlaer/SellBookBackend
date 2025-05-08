package com.analitrix.sellbook.model.core;


import java.util.Date;
import java.util.UUID;

import com.analitrix.sellbook.model.config.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
		name = "CORE_PRODUCT",
		indexes = {
				@Index(name = "IDX_PROD_CORE_BUS_ID", columnList = "CORE_BUS_ID")
		}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@Column(name = "PROD_ID")
	private UUID id= UUID.randomUUID();
	@Column(name = "PROD_BAR_CODE")
	private String barCode;
	@Column(name = "PROD_NAME")
	private String name;
	@Column(name = "PROD_DESCRIPTION")
	private String description;
	@Column(name = "PROD_COST")
	private double cost;

	@ManyToOne
	@JoinColumn(name = "CFG_STS_ID")
	private Status status;
	@ManyToOne
	@JoinColumn(name = "CORE_SUBCAT_ID")
	private SubCategory subCategory;
	@ManyToOne
	@JoinColumn(name = "CORE_BUS_ID")
	private Business business;
}
