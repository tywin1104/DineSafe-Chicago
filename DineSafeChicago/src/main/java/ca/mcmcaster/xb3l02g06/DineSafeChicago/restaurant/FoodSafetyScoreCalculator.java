package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.Inspection;

/**
 * This class serves as a calculator for foodSafetyScore of the input restaurant
 * @author shens12
 * @version April 1, 2019
 *
 */
public class FoodSafetyScoreCalculator {

	/**
	 * Calculate the foodSafetyScore for the restaurant
	 * @param restaurant - the input restaurant whose foodSafetyScore will be calculated
	 * @return foodSafetyScore - the score of the input restaurant
	 * 
	 */
	public static double calculate(Restaurant restaurant) {
		double foodSafetyScore = 0;
		double RECENT_INSPECTION = 20;
		double CONSISTENCY = 20;
		double INSPECTION_DETAILS = 60;

		// sort the inspection array
		sortInspections(restaurant);

		// 20%: recent inspection
		if (recentInspection(restaurant.getInspections().get(0))) {
			foodSafetyScore += RECENT_INSPECTION;
		}

		// 20%: pass rate (consistency)
		foodSafetyScore += (CONSISTENCY * passRate(restaurant.getInspections()));

		// 60%: latest inspections (up to five inspections)
		switch (restaurant.getInspections().size()) {
		case 1:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(1, restaurant) / 100);
			break;
		case 2:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(2, restaurant) / 100);
			break;
		case 3:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(3, restaurant) / 100);
			break;
		case 4:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(4, restaurant) / 100);
			break;
		default:
			foodSafetyScore += INSPECTION_DETAILS * (weightedGrade(5, restaurant) / 100);
			break;
		}
		return foodSafetyScore;
	}

	/**
	 * Sort all inpections of the imput restaurant by inspection time from newest to oldest
	 * Selection sort is adopted because of the limited number of inspections per restaurant
	 * @param restaurant - input restaurant
	 */
	private static void sortInspections(Restaurant restaurant) {
		int n = restaurant.getInspections().size();
		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++) {
				if (restaurant.getInspections().get(j).getTime()
						.compareTo(restaurant.getInspections().get(min_idx).getTime()) > 0) {
					min_idx = j;
				}
			}
			Inspection temp = restaurant.getInspections().get(min_idx);
			restaurant.getInspections().set(min_idx, restaurant.getInspections().get(i));
			restaurant.getInspections().set(i, temp);
		}

	}

	/**
	 * Check if the input inspection is performed within one year from today
	 * @param latestInspection - newest inspection record performed on the specific restaruant
	 * @return boolean value - If the input inspection is performed within one year from today, return true., otherwise return false
	 */
	private static boolean recentInspection(Inspection latestInspection) {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR,-1);
		Date lastYear = cal.getTime();
		return latestInspection.getTime().after(lastYear);
	}

	/**
	 * calculate the pass rate (including pass and conditional pass) of the restaurant calculated by numbers of passes divided by total number of inspections
	 * @param array - an array of inspections
	 * @return double value - pass rate
	 */
	private static double passRate(List<Inspection> array) {
		int pass = 0;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getResult() == "Pass" || array.get(i).getResult() == "Pass w/ Conditions") {
				pass++;
			}
		}
		return ((double)pass / array.size());
	}

	/**
	 * calculate the weighted grade based on at most five latest inspections performed on the specific restaurant
	 * Newest inspections will be assigned more weight proportion
	 * @param numberOfNewInspections - an integer representing the number of inspections to be considered
	 * @param restaurant - input restaurant whose weighted grade will be calculated
	 * @return weightedGrade - overall grades based on results of latest inspections
	 */
	private static double weightedGrade(int numberOfNewInspections, Restaurant restaurant) {
		double weightedGrade = 0;
		double denominator = (1 + numberOfNewInspections) * numberOfNewInspections / 2.0;
		double numerator = 1;
		for (int i = numberOfNewInspections - 1; i >= 0; i--) {
			weightedGrade += restaurant.getInspections().get(i).CalcInspectionScore() * (numerator / denominator);
			numerator++;
		}
		return weightedGrade;
	}
}
