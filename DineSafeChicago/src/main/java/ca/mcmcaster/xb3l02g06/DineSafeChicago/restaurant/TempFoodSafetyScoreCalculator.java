package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import main.Inspection.*;

public class Restaurant implements Comparable<Restaurant> {
	private final String name;
	private final int id;
	private final int zip;
	private final double latitude;
	private final double longitude;
	private ArrayList<Inspection> inspections;
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
		this.inspections = new ArrayList<Inspection>();
	}
	
	public ArrayList<Inspection> getInspections() {
		return inspections;
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
	
	public double getFoodSafetyScore() {
		return this.foodSafetyScore;
	}
	
	public boolean isClosed() {
		return isClosed();
	}
	
	
	public void calculateFoodSafetyScore() {
		double score = 0;
		double RECENT_INSPECTION = 20;
		double CONSISTENCY = 20;
		double INSPECTION_DETAILS = 60;
		
		// sort the inspection array
		//System.out.println("Original Array " + this.inspections);
		sortInspections();
		//System.out.println("Sorted Array " + this.inspections);
		
		// 20%: recent inspection
		if(recentInspection(this.inspections.get(0))) {
			score += RECENT_INSPECTION;
		}
		//System.out.println("Score after first step " + score);
		
		// 20%: pass rate (consistency)
		score += (CONSISTENCY*passRate(this.inspections));
		//System.out.println("Pass Rate is " + passRate(this.inspections));
		//System.out.println("Socre after second step " + score);
		
		// 60%: latest inspections (up to five inspections)
		switch(this.inspections.size())
		{
		case 1:
			score += INSPECTION_DETAILS * (weightedGrade(1) / 100);
			break;
		case 2:
			score += INSPECTION_DETAILS * (weightedGrade(2) / 100);
			break;
		case 3:
			score += INSPECTION_DETAILS * (weightedGrade(3) / 100);
			break;
		case 4:
			score += INSPECTION_DETAILS * (weightedGrade(4) / 100);
			break;
		default:
			score += INSPECTION_DETAILS * (weightedGrade(5) / 100);
			break;
		}
		//System.out.println("Final Score " + score);
		this.foodSafetyScore = score;
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
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR,-1);
		Date lastYear = cal.getTime();
		return latestInspection.getTime().after(lastYear);
	}
	
	private double passRate(ArrayList<Inspection> array) {
		int pass = 0;
		for (int i=0; i<array.size(); i++) {
			if(array.get(i).getResult() == ResultT.PASS || array.get(i).getResult() == ResultT.CONDITIONALPASS) {
				pass++;
			}
		}
		//System.out.println("Passes " + pass + " times");
		//System.out.println("Totol Inspections " + (array.size()));
		return ((double)pass/array.size());
	}
	
	private double weightedGrade (int numberOfNewInspections) {
		double weightedGrade = 0;
		double denominator = (1+numberOfNewInspections)*numberOfNewInspections/2.0;
		double numerator = 1;
		for (int i=numberOfNewInspections-1; i>=0; i--) {
			//System.out.println("The score of " + i + " Record is " + this.inspections.get(i).CalcInspectionScore());
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
	
	public static void main(String[] args) {
		Inspection inspection1 = new Inspection(ResultT.CONDITIONALPASS,"1 PERSON MAINTAIN","01/02/2018");
		Inspection inspection2 = new Inspection(ResultT.INVALID,"41 WIPING CLOTAIN","03/05/2019");
		Inspection inspection3 = new Inspection(ResultT.PASS,"","12/05/2019");
		Inspection inspection4 = new Inspection(ResultT.FAIL,"23 aaaaaa","03/05/2017");
		
		Restaurant res1 = new Restaurant ("aaa", 1114, 1141, 114.1, 411.1);
		Restaurant res2 = new Restaurant("aab", 1121, 1121, 121.1, 121.1);
		Restaurant res3 = new Restaurant("aas", 1131, 1131, 131.1, 131.1);
		Restaurant res4 = new Restaurant("aaf", 1111, 1211, 111.1, 121.1);
		
		res1.addInspection(inspection1);
		res1.addInspection(inspection2);
		res1.addInspection(inspection3);
		res1.addInspection(inspection4);
		
		res2.addInspection(inspection2);
		res2.addInspection(inspection1);
		
		res3.addInspection(inspection3);
		
		res4.addInspection(inspection3);
		res4.addInspection(inspection4);
		res4.addInspection(inspection1);
		
		res1.calculateFoodSafetyScore();
		res2.calculateFoodSafetyScore();
		res3.calculateFoodSafetyScore();
		res4.calculateFoodSafetyScore();
		
	}
}
