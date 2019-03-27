package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.util.ArrayList;
import java.util.Date;
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

	// Calculate Food Safety Score

	private void sortInspections() {
		int n = this.inspections.size();
		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++) {
				if (this.inspections.get(j).getTime().compareTo(this.inspections.get(min_idx).getTime()) > 0) {
					min_idx = j;
				}
			}
			Inspection temp = this.inspections.get(min_idx);
			this.inspections.set(min_idx, this.inspections.get(i));
			this.inspections.set(i, temp);
		}

	}

	public void calculateFoodSafetyScore() {
		double foodSafetyScore = 0;
		double RECENT_INSPECTION = 20;
		double CONSISTENCY = 20;
		double INSPECTION_DETAILS = 60;

		// sort the inspection array
		sortInspections();

		// 20%: recent inspection
		if (recentInspection(this.inspections.get(0))) {
			foodSafetyScore += RECENT_INSPECTION;
		}

		// 20%: pass rate (consistency)
		foodSafetyScore += (CONSISTENCY * passRate(this.inspections));

		// 60%: latest inspections (up to five inspections)
		switch (this.inspections.size()) {
		case 1:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(1) / 100);
			break;
		case 2:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(2) / 100);
			break;
		case 3:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(3) / 100);
			break;
		case 4:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(4) / 100);
			break;
		default:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(5) / 100);
			break;
		}

		this.foodSafetyScore = foodSafetyScore;
	}

	private boolean recentInspection(Inspection latestInspection) {
		@SuppressWarnings("deprecation")
		Date recentDate = new Date(2018, 4, 1);
		return latestInspection.getTime().after(recentDate);
	}

	private double passRate(List<Inspection> array) {
		int pass = 0;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getResult() == "Pass" || array.get(i).getResult() == "Pass w/ Conditions") {
				pass++;
			} 
		}
		return (double) (pass / array.size());
	}

	private double weightedGrade(int numberOfNewInspections) {
		double weightedGrade = 0;
		double denominator = (1 + numberOfNewInspections) * numberOfNewInspections / 2.0;
		double numerator = 1;
		for (int i = numberOfNewInspections - 1; i >= 0; i++) {
			weightedGrade += this.inspections.get(i).CalcInspectionScore() * (numerator / denominator);
			numerator++;
		}
		return weightedGrade;
	}
}
