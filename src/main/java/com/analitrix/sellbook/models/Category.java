package com.analitrix.sellbook.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
	@Id
	private String id= UUID.randomUUID().toString();
	@Column(unique = true)
	private String name;
}
