package com.store.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column()
	private String username;

	@Column()
	private String password;

	@Column()
	private String fullname;

	@Column()
	private String email;

	@Column()
	private String photo;
	
	@Column()
	private String token;

	@Column()
	private Boolean activated;

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Authority> authorities;

	@JsonIgnore
	@OneToMany(mappedBy = "createdUser")
	private List<Product> productsCU;

	@JsonIgnore
	@OneToMany(mappedBy = "lastModifiedUser")
	private List<Product> productsLMU;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;

}
