package com.weshopifyplatform.app.entities;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Categories implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2857332108550225557L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, unique = true)
	private String name;
	private String aliasName;
	private String imagePath;
	
	
	private boolean isEnabled;
	
	@ManyToOne
	@JoinTable(name = "parent_childs",
	joinColumns = {
			@JoinColumn(name = "child_id",referencedColumnName = "id")},
		inverseJoinColumns = {
				@JoinColumn(name="parent_id",referencedColumnName = "id",unique = false)}	 
	)
	private Categories parent;
	

}
