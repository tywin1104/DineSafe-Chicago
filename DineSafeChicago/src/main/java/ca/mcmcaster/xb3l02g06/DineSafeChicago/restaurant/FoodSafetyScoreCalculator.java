package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.Inspection;

public class FoodSafetyScoreCalculator {

	public static double calculate(Restaurant restaurant) {
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
		return score;
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
			if(array.get(i).getResult() == "Pass" || array.get(i).getResult() == "Pass w/ Conditions") {
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
}
