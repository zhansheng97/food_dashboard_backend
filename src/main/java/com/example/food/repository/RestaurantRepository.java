package com.example.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.food.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	Optional<Restaurant> findRestaurantById(Long id);
	
	@Query(value = "SELECT * FROM restaurant WHERE name LIKE %:name%" ,nativeQuery = true)
	Optional<List<Restaurant>> findAllByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM restaurant WHERE res_type LIKE %:resType%" ,nativeQuery = true)
	Optional<List<Restaurant>> findAllByResType(@Param("resType") String resType);
	
	@Query(value = "SELECT * FROM restaurant WHERE distance < ?1" ,nativeQuery = true)
	Optional<List<Restaurant>> findAllByDistance(String distance);
	
	@Query(value = "SELECT * FROM restaurant WHERE rating > ?1" ,nativeQuery = true)
	Optional<List<Restaurant>> findAllByRating(String rating);
	
	@Query(value = "SELECT * FROM restaurant WHERE price < ?1" ,nativeQuery = true)
	Optional<List<Restaurant>> findAllByPrice(String price);
	

}
