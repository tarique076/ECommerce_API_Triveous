package com.triveous.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	
	@OneToOne(mappedBy = "cart")
	@JoinColumn(name = "cartId")
	@JsonIgnore
	private Users user;
	
	private Integer quantity;
	
	private Double totalCost;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="cart_products")
	private List<CartProductDto> cartProducts = new ArrayList<>();
	
	private Integer orderId;
}
