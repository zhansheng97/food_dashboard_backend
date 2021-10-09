package com.example.food.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.food.exception.RestaurantException;
import com.example.food.firesbaseservice.FirebaseService;
import com.example.food.model.Restaurant;
import com.example.food.model.RestaurantRequest;
import com.example.food.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final FirebaseService firebaseService;
	
	public List<Restaurant> getAllRestaurant() {
		return restaurantRepository.findAll();
	}
	
	public Restaurant addRestaurant(RestaurantRequest restaurantRequest) throws IOException {
		String imageUrl = firebaseService.upload(restaurantRequest);
		Restaurant restaurant = Restaurant.builder()
			.imageUrl(imageUrl)
			.name(restaurantRequest.getName())
			.price(restaurantRequest.getPrice())
			.rating(restaurantRequest.getRating())
			.distance(restaurantRequest.getDistance())
			.resType(restaurantRequest.getResType())
			.build();
		
		return restaurantRepository.save(restaurant);
	}

	public Restaurant getRestaurantById(Long id) {
		return restaurantRepository.findRestaurantById(id)
				.orElseThrow(() -> 
				new RestaurantException(String.format("Restaurant with id %s couldn't be found", id)));
		
	}

	public Restaurant updateRestaurant(Long id, RestaurantRequest restaurant) throws IOException {
		Restaurant optionalRestaurant = restaurantRepository.findRestaurantById(id)
				.orElseThrow(()->new RestaurantException("Restaurant with id " + id +" not found"));
		
		if (restaurant.getFileName() != null) {
			String imageUrl = firebaseService.upload(restaurant);
			optionalRestaurant.setImageUrl(imageUrl);
		} else {
			optionalRestaurant.setImageUrl(restaurant.getImageUrl());
		}
		
		optionalRestaurant.setName(restaurant.getName());
		optionalRestaurant.setResType(restaurant.getResType());
		optionalRestaurant.setRating(restaurant.getRating());
		optionalRestaurant.setPrice(restaurant.getPrice());
		optionalRestaurant.setDistance(restaurant.getDistance());
		
		return restaurantRepository.save(optionalRestaurant);
	}

	public void deleteRestaurant(Long id) {
		Restaurant restaurant = restaurantRepository.findRestaurantById(id)
				.orElseThrow(()->new RestaurantException("Restaurant with id " + id +" not found"));
		
		 restaurantRepository.delete(restaurant);
	}

	public List<Restaurant> getRestaurantByName(String name) {
		return  restaurantRepository.findAllByName(name)
				.orElseThrow(() -> new RestaurantException("No Restaurants found with common name = " + name));
		
	}
	
	public List<Restaurant> getRestaurantByResType(String resType) {
		return  restaurantRepository.findAllByResType(resType)
				.orElseThrow(() -> new RestaurantException("No Restaurants found with type = " + resType));
		
	}
	
	public List<Restaurant> getRestaurantByDistance(String distance) {
		return  restaurantRepository.findAllByDistance(distance)
				.orElseThrow(() -> new RestaurantException("No Restaurants found with distance <  " + distance));
		
	}
	
	public List<Restaurant> getRestaurantByRating(String rating) {
		return  restaurantRepository.findAllByRating(rating)
				.orElseThrow(() -> new RestaurantException("No Restaurants found with rating > " + rating));
		
	}
	
	public List<Restaurant> getRestaurantByPrice(String price) {
		return  restaurantRepository.findAllByPrice(price)
				.orElseThrow(() -> new RestaurantException("No Restaurants found with price < " + price));
		
	}
	
	
}
