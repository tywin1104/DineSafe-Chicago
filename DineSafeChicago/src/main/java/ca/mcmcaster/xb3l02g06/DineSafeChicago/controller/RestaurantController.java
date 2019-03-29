package ca.mcmcaster.xb3l02g06.DineSafeChicago.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantRepository;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantService;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private RestaurantRepository restaurantRepo;

	@RequestMapping("/restaurants/{zip}")
	public ArrayList<Restaurant> getTopRestaurantsByZip(@PathVariable int zip) {
		ArrayList<Restaurant> restaurants = restaurantService.getTopRestaurantsByZip(zip);
		return restaurants;
	}
}
