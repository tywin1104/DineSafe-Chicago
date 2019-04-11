package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.Inspection;

/**
 * Represents a restaurant.
 * 
 * @author Tianyi Zhang
 */

@Entity
public class Restaurant implements Comparable<Restaurant> {

	@EmbeddedId
	private RestaurantIdentity restaurantIdentity;

	private int zip;
	private int licenseNum;
	private double latitude;
	private double longitude;
	private double foodSafetyScore;
	private double neighborhoodSafetyScore;
	private HashMap<String, Integer> crimesCount;
	private double overallScore;
	private boolean closed;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Inspection> inspections;
	protected Restaurant() {}

	/**
	 * Constructor for a restaurant with the specified field values.
	 * 
	 * @param restaurantIdentity identity entity encapsulating restaurant's address
	 *                           and name
	 * @param zip                the zip code of the restaurant
	 * @param latitude           the latitude of the restaurant
	 * @param longitude          the longitude of the restaurant
	 * @param licenseNum         the license number of the restaurant
	 */
	public Restaurant(RestaurantIdentity restaurantIdentity, int zip, double latitude, double longitude,
			int licenseNum) {
		this.restaurantIdentity = restaurantIdentity;
		this.zip = zip;
		this.latitude = latitude;
		this.longitude = longitude;
		this.closed = false;
		this.licenseNum = licenseNum;
		this.foodSafetyScore = 0;
		this.neighborhoodSafetyScore = 0;
		this.crimesCount = new HashMap<String, Integer>();
		this.initCrimeCount();
	}

	private void initCrimeCount() {
		crimesCount.put("High", 0);
		crimesCount.put("Medium", 0);
		crimesCount.put("Low", 0);
	}

	/**
	 * Add one inspection history to the current restaurant.
	 * 
	 * @param ins the Inspection object to be added to the current restaurant
	 */
	public void addInspection(Inspection ins) {
		if (inspections == null)
			inspections = new ArrayList<Inspection>();
		inspections.add(ins);
		ins.setRestaurant(this);
	}

	/**
	 * Get food inspection history for this restaurant
	 * 
	 * @return a list of inspections
	 */
	public List<Inspection> getInspections() {
		return inspections;
	}

	/**
	 * Get zipcode for this restaurant
	 * 
	 * @return the zip code for this restaurant
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * Set zipcode for this restaurant
	 * 
	 * @param the zip code for this restaurant
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}

	/**
	 * Get license number for this restaurant
	 * 
	 * @return the license number for this restaurant
	 */
	public int getLicenseNum() {
		return licenseNum;
	}

	/**
	 * Set license number for this restaurant
	 * 
	 * @param the license number for this restaurant
	 */
	public void setLicenseNum(int licenseNum) {
		this.licenseNum = licenseNum;
	}

	/**
	 * Get latitude for this restaurant
	 * 
	 * @return the latitude for this restaurant
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Set latitude for this restaurant
	 * 
	 * @param the latitude for this restaurant
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Get longitude for this restaurant
	 * 
	 * @return the longitude for this restaurant
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Set longitude for this restaurant
	 * 
	 * @param the longitude for this restaurant
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get food safety score for this restaurant
	 * 
	 * @return the food safety score for this restaurant
	 */
	public double getFoodSafetyScore() {
		return foodSafetyScore;
	}

	/**
	 * Set food safety score for this restaurant
	 * 
	 * @param the food safety score for this restaurant
	 */
	public void setFoodSafetyScore(double foodSafetyScore) {
		this.foodSafetyScore = foodSafetyScore;
	}

	/**
	 * Get neighborhood safety score for this restaurant
	 * 
	 * @return the neighborhood safety score for this restaurant
	 */
	public double getNeighborhoodSafetyScore() {
		return neighborhoodSafetyScore;
	}

	/**
	 * Set neighborhood safety score for this restaurant
	 * 
	 * @param the neighborhood safety score for this restaurant
	 */
	public void setNeighborhoodSafetyScore(double neighborhoodSafetyScore) {
		this.neighborhoodSafetyScore = neighborhoodSafetyScore;
	}

	/**
	 * Get overall score for this restaurant
	 * 
	 * @return the overall safety score for this restaurant
	 */
	public double getOverallScore() {
		return overallScore;
	}

	/**
	 * Set overall score for this restaurant
	 * 
	 * @param the overall safety score for this restaurant
	 */
	public void setOverallScore(double overallScore) {
		this.overallScore = overallScore;
	}

	/**
	 * Check if the current restaurant is closed
	 * 
	 * @return if the current restaurant is closed
	 */
	public boolean isClosed() {
		return closed;
	}

	/**
	 * Get the mapping for crimes nearby this restaurant
	 * 
	 * @return the mapping for crimes nearby this restaurant
	 */
	public HashMap<String, Integer> getCrimesCount() {
		return crimesCount;
	}

	/**
	 * Set the mapping for crimes nearby this restaurant
	 * 
	 * @param the mapping for crimes nearby this restaurant
	 */
	public void setCrimesCount(HashMap<String, Integer> crimesCount) {
		this.crimesCount = crimesCount;
	}

	/**
	 * Set the closed field for the restaurant
	 * 
	 * @param boolean value to set the closed field value
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	/**
	 * Establish the relative ordering among restaurants
	 * 
	 * @param the other restaurant object to compare with
	 */
	public int compareTo(Restaurant other) {
		return Double.compare(other.overallScore, overallScore);
	}

	/**
	 * Get the restaurant identity entity for this restaurant
	 * 
	 * @return the restaurant identity entity for this restaurant
	 */
	public RestaurantIdentity getRestaurantIdentity() {
		return restaurantIdentity;
	}

	/**
	 * Set the restaurant identity entity for this restaurant
	 * 
	 * @param the restaurant identity entity for this restaurant
	 */
	public void setRestaurantIdentity(RestaurantIdentity restaurantIdentity) {
		this.restaurantIdentity = restaurantIdentity;
	}

	/**
	 * Get the string representation of this restaurant
	 * 
	 * @return the string representation of this restaurant
	 */
	@Override
	public String toString() {
		return "Restaurant [name=" + this.restaurantIdentity.getName() + ", overallScore=" + overallScore + "]";
	}
}
