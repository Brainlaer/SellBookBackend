package com.analitrix.sellbook.model.core;


import com.analitrix.sellbook.model.config.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
		name = "CORE_CATEGORY",
		indexes = {
				@Index(name = "IDX_CAT_CORE_BUS_ID", columnList = "CORE_BUS_ID")
		}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	@Id
	@Column(name = "CAT_ID")
	private UUID id= UUID.randomUUID();
	@Column(name = "CAT_NAME", unique = true)
	private String name;
	@Column(name = "CAT_DESCRIPTION")
	private String description;

	@ManyToOne
	@JoinColumn(name = "CORE_BUS")
	private Business business;
	@ManyToOne
	@JoinColumn(name = "CFG_STS_ID")
	private Status status;
}
