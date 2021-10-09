package com.example.food.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.Restaurant;
import com.example.food.model.RestaurantRequest;
import com.example.food.service.RestaurantService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/restaurant")
@CrossOrigin
public class RestaurantController {
	
	private final RestaurantService restaurantService;
	
	 private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);
	
	 // Get All Restaurant
	@GetMapping("/all")
	public ResponseEntity<List<Restaurant>> getAllRestaurant() {
		List<Restaurant> restaurants = restaurantService.getAllRestaurant();
		return ResponseEntity.status(HttpStatus.OK).body(restaurants);
	}
	
	// Add Restaurant
	@PostMapping("/add")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody RestaurantRequest restaurantRequest) throws IOException {
		 Restaurant addedRestaurant = restaurantService.addRestaurant(restaurantRequest);
		 return ResponseEntity.status(HttpStatus.CREATED).body(addedRestaurant);
	}
	
	// Get Restaurant By id
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") Long id) {
		Restaurant restaurant = restaurantService.getRestaurantById(id);
		return ResponseEntity.status(HttpStatus.OK).body(restaurant);
	}
	
	// Update Restaurant
	@PutMapping("/update/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantRequest restaurantRequest) throws IOException {
		Restaurant restaurant = restaurantService.updateRestaurant(id, restaurantRequest);
		return ResponseEntity.status(HttpStatus.OK).body(restaurant);
	}
	
	// Delete Restaurant
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRestaurant(@PathVariable("id") Long id) {
		 restaurantService.deleteRestaurant(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// Get Restaurants By Name
	@GetMapping("/name={name}")
	public ResponseEntity<List<Restaurant>> getRestaurantByName(@PathVariable("name") String name) {
		List<Restaurant> restaurants = restaurantService.getRestaurantByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(restaurants);
	}
	
	// Get Restaurants By Type 
	@GetMapping("/resType={resType}")
	public ResponseEntity<List<Restaurant>> getRestaurantByResType(@PathVariable("resType") String resType) {
		List<Restaurant> restaurants = restaurantService.getRestaurantByResType(resType);
		return ResponseEntity.status(HttpStatus.OK).body(restaurants);
	}
	
	// Get Restaurants By Distance 
	@GetMapping("/distance={distance}")
	public ResponseEntity<List<Restaurant>> getRestaurantByDistance(@PathVariable("distance") String distance) {
		List<Restaurant> restaurants = restaurantService.getRestaurantByDistance(distance);
		return ResponseEntity.status(HttpStatus.OK).body(restaurants);
	}

	// Get Restaurants By Rating
	@GetMapping("/rating={rating}")
	public ResponseEntity<List<Restaurant>> getRestaurantByRating(@PathVariable("rating") String rating) {
		List<Restaurant> restaurants = restaurantService.getRestaurantByRating(rating);
		return ResponseEntity.status(HttpStatus.OK).body(restaurants);
	}
	
	// Get Restaurants By Price
	@GetMapping("/price={price}")
	public ResponseEntity<List<Restaurant>> getRestaurantByPrice(@PathVariable("price") String price) {
		List<Restaurant> restaurants = restaurantService.getRestaurantByPrice(price);
		return ResponseEntity.status(HttpStatus.OK).body(restaurants);
	}
	
	
	

//	// Download Restaurant Images
//    @PostMapping("/pic/{fileName}")
//    public Object download(@PathVariable String fileName) throws IOException {
//        logger.info("HIT -/download | File Name : {}", fileName);
//        return firebaseService.download(fileName);
//    }
   
}
