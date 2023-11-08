package com.triveous.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;
	
	private String categoryName;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<>();
}
