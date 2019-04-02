package ca.mcmcaster.xb3l02g06.DineSafeChicago.jobs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import au.com.bytecode.opencsv.CSVReader;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HashTable;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.Inspection;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.InspectionRepository;
//import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.FoodSafetyScoreCalculator;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantIdentity;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantRepository;

@SpringBootApplication
//@Profile("test")
@ComponentScan(basePackages = { "ca.mcmcaster.xb3l02g06.DineSafeChicago" })
public class DataLoader implements CommandLineRunner {

	@Autowired
	private InspectionRepository inspectionRepo;

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Override
	public void run(String... args) throws Exception {
//		test();
//		loadResInspctions();  
//		updateFoodSafetyScore();
		loadCrimes();
	}

	public static void main(String[] args) {
		SpringApplication.run(DataLoader.class, args);
	}

	public void updateFoodSafetyScore() {
		int counter = 0;
		for (Restaurant restaurant : restaurantRepo.findAll()) {
			counter++;
//			System.out.println("Updating... " + counter);
//			System.out.println(FoodSafetyScoreCalculator.calculate(restaurant));
//			restaurant.calculateFoodSafetyScore(); 
			restaurantRepo.save(restaurant);
		}
	}

	public void loadResInspctions() throws FileNotFoundException {
		String fileIn = "/Users/tianyizhang/Desktop/McMaster/CS2XB3/DineSafe-Chicago/DineSafeChicago/src/main/resources/data/InspectionFiltered.csv";

		CSVReader reader = new CSVReader(new FileReader(fileIn), ',', '"', 1);
		List<String[]> allRows;
		int counter = 0;
		try {
			allRows = reader.readAll();
			for (String[] temp : allRows) {
				counter++;
				System.out.println("Processing..." + counter);
				String name = temp[1];
				int licenseNum = Integer.parseInt(temp[2]);
				String address = temp[4];
				int zip = Integer.parseInt(temp[5]);
				String dateStr = temp[6];
				String result = temp[7];
				String violation = temp[8];
				double latitude = Double.parseDouble(temp[9]);
				double longitude = Double.parseDouble(temp[10]);

				Restaurant restaurant = restaurantRepo.findByRestaurantIdentity(new RestaurantIdentity(address, name));
				if (restaurant == null) {
					RestaurantIdentity restaurantIdentity = new RestaurantIdentity(address, name);
					restaurant = new Restaurant(restaurantIdentity, zip, latitude, longitude, licenseNum);
				}
				Inspection inspection = new Inspection(result, violation, dateStr);
				// Check if that restaurant is closed
				if (result.equals("Out of Business")) {
					restaurant.setClosed(true);
				}
				restaurant.addInspection(inspection);
				restaurantRepo.save(restaurant);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadCrimes() throws IOException {
		String fileIn = "/Users/tianyizhang/Desktop/McMaster/CS2XB3/DineSafe-Chicago/DineSafeChicago/src/main/resources/data/CrimeFiltered.csv";

		int counter = 0;
		for (Restaurant restaurant : restaurantRepo.findByClosedFalse()) {
			counter++;
			System.out.println("Processing " + counter);

			double res_latitude = restaurant.getLatitude();
			double res_longitude = restaurant.getLongitude();

			int high_crime_count = 0;
			int medium_crime_count = 0;
			int low_crime_count = 0;
			double currentCrimeScore = 0;

			CSVReader reader = new CSVReader(new FileReader(fileIn), ',', '"', 0);
			List<String[]> allRows;
			allRows = reader.readAll();
			for (String[] temp : allRows) {
				String type = temp[1];
				double latitude = Double.parseDouble(temp[2]);
				double longitude = Double.parseDouble(temp[3]);
				if (Math.abs(res_latitude - latitude) <= 0.015 && Math.abs(res_longitude - longitude) <= 0.015) {
					if (type.equals("HOMICIDE"))
						high_crime_count++;
					else if (type.equals("ROBBERY") || type.equals("ASSAULT") || type.equals("KIDNAPPING")
							|| type.equals("CRIM SEXUAL ASSAULT") || type.equals("HUMAN TRAFFICKING"))
						medium_crime_count++;
					else
						low_crime_count++;
				}
			}
			currentCrimeScore = (high_crime_count * 20) + (medium_crime_count * 5) + (low_crime_count * 1);
			HashMap<String, Integer> crimesCount = new HashMap<String, Integer>();
			crimesCount.put("High", high_crime_count);
			crimesCount.put("Medium", medium_crime_count);
			crimesCount.put("Low", low_crime_count);
			restaurant.setCrimesCount(crimesCount);
			restaurant.setNeighborhoodSafetyScore(currentCrimeScore);
			restaurantRepo.save(restaurant);
		}
	}
}
