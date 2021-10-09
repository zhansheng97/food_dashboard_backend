package com.example.food.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {
	
	private String name;
	private String imageUrl;
	private String resType;
	private String rating;
	private String price;
	private String distance;
	private String fileName;
	private String fileType;
	private String bytes;
	
	
}
