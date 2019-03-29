package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.util.Date;
import java.util.List;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.Inspection;

public class FoodSafetyScoreCalculator {

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

	private static boolean recentInspection(Inspection latestInspection) {
		@SuppressWarnings("deprecation")
		Date recentDate = new Date(2018, 4, 1);
		return latestInspection.getTime().after(recentDate);
	}

	private static double passRate(List<Inspection> array) {
		int pass = 0;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getResult() == "Pass" || array.get(i).getResult() == "Pass w/ Conditions") {
				pass++;
			}
		}
		return (double) (pass / array.size());
	}

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
