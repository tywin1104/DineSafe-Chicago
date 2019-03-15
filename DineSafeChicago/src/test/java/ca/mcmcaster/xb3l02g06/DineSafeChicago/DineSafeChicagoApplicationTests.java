package ca.mcmcaster.xb3l02g06.DineSafeChicago;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.bytecode.opencsv.CSVReader;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.Inspection;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.InspectionRepository;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DineSafeChicagoApplicationTests {

	@Autowired
	private InspectionRepository inspectionRepo;

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Test
	public void contextLoads() throws FileNotFoundException {

		String fileIn = "/Users/tianyizhang/Desktop/McMaster/CS2XB3/DineSafe-Chicago/DineSafeChicago/src/main/resources/data/InspectionsSample.csv";
		String line = null;

		CSVReader reader = new CSVReader(new FileReader(fileIn), ',', '"', 1);
		List<String[]> allRows;
		try {
			allRows = reader.readAll();
			for (String[] temp : allRows) {
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
