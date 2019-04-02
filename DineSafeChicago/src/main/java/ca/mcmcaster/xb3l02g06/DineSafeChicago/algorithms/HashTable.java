package main;

import java.util.ArrayList;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;

/**
 * Convert an array of restaurants to a hasetable in order to search restaurants by zipcode
 * @author Shanghong Shen, Tianyi Zhang
 * @version April 1, 2019
 * 
 */
public class HashTable {

	private int capacity;
	private ArrayList<Restaurant>[] st;

	/**
	 * Constructor
	 * @param capacity - the capacity of the hashTable
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int capacity) {
		this.capacity = capacity;
		this.st = (ArrayList<Restaurant>[]) new ArrayList[capacity];
		for (int i = 0; i < capacity; i++) {
			this.st[i] = new ArrayList<Restaurant>();
		}
	}

	/**
	 * convert an arraylist of restaurants to a hashtable using seperate chaining
	 * @param restaurants - an arraylist of restaurants
	 */
	public void loadRestaurants(ArrayList<Restaurant> restaurants) {
		for (Restaurant restaurant : restaurants)
			put(restaurant);
	}

	/**
	 * put a restaurant into corresponding linked list by its hase value
	 * @param restaurant - a restaurant to be put into the hash table
	 */
	public void put(Restaurant restaurant) {
		int zip = restaurant.getZip();
		int i = hash(zip);
		st[i].add(restaurant);
	}

	/**
	 * 
	 * @param zip - the zipcode of the restaurant
	 * @return hash value - an integer representing the hase value of the restaurant by zipcode
	 */
	private int hash(int zip) {
		return zip  % capacity;
	}

	/**
	 * Search all restaurants whose zipcode is the same as the input zipcode
	 * @param zip - zipcode as the search key to search all restaurants having this zipcode
	 * @return ArrayList - an ArrayList containing all restaurants whose zipcode is the input zipcode
	 */
	public ArrayList<Restaurant> getRestaurants(int zip) {
		int i = hash(zip);
		return st[i];
	}
}
