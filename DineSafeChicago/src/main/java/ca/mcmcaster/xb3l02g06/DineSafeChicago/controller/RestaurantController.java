package ca.mcmcaster.xb3l02g06.DineSafeChicago.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping("/test")
	public String testResponse() {
		return "HAHA";
	}
}
