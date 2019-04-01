package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.BST;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HashTable;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HeapSort;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.InspectionRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;

	public ArrayList<Restaurant> getTopRestaurantsByZip(int zip) {
		ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) restaurantRepo.findByClosedFalse();
		HashTable hashTable = new HashTable(59);
		hashTable.loadRestaurants(restaurants);
		ArrayList<Restaurant> filtered = hashTable.getRestaurants(zip);
		HeapSort.sortHeap(filtered, filtered.size());
		return filtered;
	}

	public Restaurant getRestaurantByLicenseNumber(int licenseNum) {
		ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) restaurantRepo.findByClosedFalse();
		BST<Integer, Restaurant> bst = new BST<Integer, Restaurant>();
		for (Restaurant restaurant : restaurants) {
			bst.put(restaurant.getLicenseNum(), restaurant);
		}
		return bst.get(licenseNum);
	}

}
