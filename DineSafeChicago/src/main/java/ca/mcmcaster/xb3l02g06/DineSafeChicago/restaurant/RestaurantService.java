package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HashTable;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.InspectionRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private InspectionRepository inspectionRepo;

	@Autowired
	private RestaurantRepository restaurantRepo;
		
	
	public ArrayList<Restaurant> getTopRestaurantsByZip(int zip) {
		ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) restaurantRepo.findByClosedFalse();
		HashTable hashTable = new HashTable(59);  
		hashTable.loadRestaurants(restaurants);
		ArrayList<Restaurant> filtered = hashTable.getRestaurants(zip);
		return filtered; 
	}
	

}
