package main;

import java.util.ArrayList; 
import java.util.Date;

import final_project.Inspection.ResultT;

public class Restaurant implements Comparable<Restaurant> {
	private final String name;
	private final int id;
	private final int zip;
	private final double latitude;
	private final double longitude;
	private ArrayList<Inspection> inspections;
	private ArrayList<Crime> crimes; 
	private double foodSafetyScore;
	private double neighborhoodSafetyScore;
	private double overallScore;
	private boolean closed;

	public Restaurant(String name, int id, int zip, double latitude, double longitude) {
		this.name = name;
		this.id = id;
		this.zip = zip;
		this.latitude = latitude;
		this.longitude = longitude;
		this.closed = false; 
		this.crimes = new ArrayList<Crime>();
		this.inspections = new ArrayList<Inspection>();
	}
	
	public void addInspection (Inspection inspection) {
		this.inspections.add(inspection);
	}
	
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getZip() {
		return zip;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getNeighborhoodSafetyScore() {
		return neighborhoodSafetyScore;
	}

	public double getOverallScore() {
		return overallScore;
	}
	
	public boolean isClosed() {
		return isClosed();
	}
	
	
	public void calculateFoodSafetyScore() {
		double foodSafetyScore = 0;
		double RECENT_INSPECTION = 20;
		double CONSISTENCY = 20;
		double INSPECTION_DETAILS = 60;
		
		// sort the inspection array
		sortInspections();
		
		// 20%: recent inspection
		if(recentInspection(this.inspections.get(0))) {
			foodSafetyScore += RECENT_INSPECTION;
		}
		
		// 20%: pass rate (consistency)
		foodSafetyScore += (CONSISTENCY*passRate(this.inspections));
		
		// 60%: latest inspections (up to five inspections)
		switch(this.inspections.size())
		{
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
	
	private void sortInspections() {
		int n = this.inspections.size();
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for(int j = i+1; j < n; j++) {
				if(this.inspections.get(j).getTime().compareTo(this.inspections.get(min_idx).getTime()) > 0) {
					min_idx = j;
				}
			}
			Inspection temp = this.inspections.get(min_idx);
			this.inspections.set(min_idx, this.inspections.get(i));
			this.inspections.set(i, temp);
		}
		
	}
	
	private boolean recentInspection(Inspection latestInspection) {
		Date recentDate = new Date(2018, 4, 1);
		return latestInspection.getTime().after(recentDate);
	}
	
	private double passRate(ArrayList<Inspection> array) {
		int pass = 0;
		for (int i=0; i<array.size(); i++) {
			if(array.get(i).getResult() == ResultT.PASS || array.get(i).getResult() == ResultT.CONDITIONALPASS) {
				pass++;
			}
		}
		return (double)(pass/array.size());
	}
	
	private double weightedGrade (int numberOfNewInspections) {
		double weightedGrade = 0;
		double denominator = (1+numberOfNewInspections)*numberOfNewInspections/2.0;
		double numerator = 1;
		for (int i=numberOfNewInspections-1; i>=0; i++) {
			weightedGrade += this.inspections.get(i).CalcInspectionScore()*(numerator/denominator);
			numerator ++;
		}
		return weightedGrade;
	}
	
	public void calculateNeighborhoodSafetyScore() {
		//TODO
//		NeighborhoodSafetyScore =  
	}
	
	
	public int compareTo(Restaurant other) {
		return Double.compare(overallScore, other.overallScore);
	}
	
	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", overallScore=" + overallScore + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + zip;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (zip != other.zip)
			return false;
		return true;
	}
}
