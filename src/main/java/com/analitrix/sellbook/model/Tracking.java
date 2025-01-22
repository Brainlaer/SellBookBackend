package com.analitrix.sellbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="trackings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tracking {
	
	@Id
	private String id = UUID.randomUUID().toString();
	private String status; //Accepted, Preparing, On the way, Delivered
}
