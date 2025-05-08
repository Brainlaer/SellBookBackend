package com.analitrix.sellbook.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
		name="CORE_ORDER_DETAIL",
		indexes = {
				@Index(name = "IDX_ORD_DET_CORE_BUS_ID", columnList = "CORE_BUS_ID")
		}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	@Id
	@Column(name = "ORD_DET_ID")
	private UUID id= UUID.randomUUID();
	@Column(name = "ORD_DET_QUANTITY")
	private int quantity;
	@Column(name = "ORD_DET_COST")
	private double cost;

	@ManyToOne
	@JoinColumn(name = "CORE_ORD_ID")
	private Order order;
	@ManyToOne
	@JoinColumn(name = "CORE_PROD_ID")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "CORE_BUS_ID")
	private Business business;
}
