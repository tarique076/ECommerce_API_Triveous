package com.triveous.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {

	@Id
	private Integer productId;
	
	private String title;
	
	private Integer quantity;
	
	private Double price;
	
}
