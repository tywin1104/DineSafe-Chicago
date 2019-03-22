package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.Inspection;

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
	private double overallScore;
	private boolean closed;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Inspection> inspections;

	protected Restaurant() {
	}
	
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
	}

	public void addInspection(Inspection ins) {
		if (inspections == null)
			inspections = new ArrayList<Inspection>();
		inspections.add(ins);
		ins.setRestaurant(this);
	}

	public List<Inspection> getInspections() {
		return inspections;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(int licenseNum) {
		this.licenseNum = licenseNum;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}

	public double getFoodSafetyScore() {
		return foodSafetyScore;
	}

	public void setFoodSafetyScore(double foodSafetyScore) {
		this.foodSafetyScore = foodSafetyScore;
	}

	public double getNeighborhoodSafetyScore() {
		return neighborhoodSafetyScore;
	}

	public void setNeighborhoodSafetyScore(double neighborhoodSafetyScore) {
		this.neighborhoodSafetyScore = neighborhoodSafetyScore;
	}

	public double getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(double overallScore) {
		this.overallScore = overallScore;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public int compareTo(Restaurant other) {
		return Double.compare(overallScore, other.overallScore);
	}

	public RestaurantIdentity getRestaurantIdentity() {
		return restaurantIdentity;
	}

	public void setRestaurantIdentity(RestaurantIdentity restaurantIdentity) {
		this.restaurantIdentity = restaurantIdentity;
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + this.restaurantIdentity.getName() + ", overallScore=" + overallScore + "]";
	}
}
