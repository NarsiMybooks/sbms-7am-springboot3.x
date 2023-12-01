package com.weshopifyplatform.app.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
//@Table(name = "WeshopifyCustomer")
@Data
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2767152150423849249L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private String firstName;
	private String lastName;
	
	@Column(name = "customerLogin",nullable = false,unique = true)
	private String userName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(unique = true,nullable = false)
	private String email;
	
	@Column(unique = true,nullable = false)
	private String mobile;

}
