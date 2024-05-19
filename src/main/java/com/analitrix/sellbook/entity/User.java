package com.analitrix.sellbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@Column(unique = true)
	private Long id;
	private String name;
	private String surname;
	@Column(unique = true)
	private Long phone;
	@Column(unique = true)
	private String mail;
	private String password;
	@Column(name="home_address")
	private String homeAddress;
}
