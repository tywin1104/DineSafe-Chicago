package ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms;

import java.util.ArrayList;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;

public class HashTable {

	private int capacity;
	private ArrayList<Restaurant>[] st;

	// Constructor
	@SuppressWarnings("unchecked")
	public HashTable(int capacity) {
		this.capacity = capacity;
		this.st = (ArrayList<Restaurant>[]) new ArrayList[capacity];
		for (int i = 0; i < capacity; i++) {
			this.st[i] = new ArrayList<Restaurant>();
		}
	}

	public void loadRestaurants(ArrayList<Restaurant> restaurants) {
		for (Restaurant restaurant : restaurants)
			put(restaurant);
	}

	public void put(Restaurant restaurant) {
		int zip = restaurant.getZip();
		int i = hash(zip);
		st[i].add(restaurant);
	}

	private int hash(int zip) {
		return (zip & 0x7fffffff) % capacity;
	}

	public ArrayList<Restaurant> getRestaurants(int zip) {
		int i = hash(zip);
		return st[i];
	}
}
