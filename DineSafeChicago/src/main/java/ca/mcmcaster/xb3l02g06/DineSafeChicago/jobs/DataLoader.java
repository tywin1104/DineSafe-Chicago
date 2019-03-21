package ca.mcmcaster.xb3l02g06.DineSafeChicago.jobs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import au.com.bytecode.opencsv.CSVReader;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.Inspection;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.InspectionRepository;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantRepository;

@SpringBootApplication
@ComponentScan(basePackages = { "ca.mcmcaster.xb3l02g06.DineSafeChicago" })
public class DataLoader implements CommandLineRunner {

	@Autowired
	private InspectionRepository inspectionRepo;

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Override
	public void run(String... args) throws Exception {
		loadResInspctions();
	}

	public static void main(String[] args) {
		SpringApplication.run(DataLoader.class, args);
	}

	public void loadResInspctions() throws FileNotFoundException {
		//TODO: out of business -> closed,  parallel processing

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

				Restaurant restaurant = restaurantRepo.findByLicenseNum(licenseNum);
				if (restaurant == null) {
					restaurant = new Restaurant(name, zip, latitude, longitude, licenseNum, address);
				}
				Inspection inspection = new Inspection(result, violation, dateStr);
				restaurant.addInspection(inspection);
				restaurantRepo.save(restaurant);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
