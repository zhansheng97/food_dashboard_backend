package com.example.food.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String imageUrl;
	private String name;
	private String resType;
	private String rating;
	private String price;
	private String distance;
	
	public Restaurant(
			String imageUrl, 
			String name, 
			String resType, 
			String rating, 
			String price, 
			String distance) {
		this.imageUrl = imageUrl;
		this.name = name;
		this.resType = resType;
		this.rating = rating;
		this.price = price;
		this.distance = distance;
	}
	
	
}
