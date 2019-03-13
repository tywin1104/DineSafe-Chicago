package ca.mcmcaster.xb3l02g06.DineSafeChicago;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.bytecode.opencsv.CSVReader;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.InspectionRepository;
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
			for (String[] temp : allRows.subList(1, allRows.size())) {
				String name = temp[1]; 
				int licenseId = Integer.parseInt(temp[2]);
				String address = temp[4];
				int zip = Integer.parseInt(temp[5]);
				String dateStr = temp[6];
				String result = temp[7];
				String violation = temp[8]; 
				double latitude = Double.parseDouble(temp[9]);
				double longitude = Double.parseDouble(temp[10]);
				System.out.println(latitude);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

 

//				Restaurant newRestaurant = new Restaurant(name, zip, latitude, longitude, licenseId);
//		Restaurant r1 = new Restaurant("asd", 100, 12.2, 12.2, 10002);
//		Inspection i1 = new Inspection("pass", "no comment", "11/04/1998");
//		System.out.println(r1);
//		System.out.println(i1);
//		Inspection i2 = new Inspection("pass with condition", "no comment", "11/04/2019");
//		r1.addInspection(i1);
//		r1.addInspection(i2);
//		restaurantRepo.save(r1); 
//		for(Inspection ins: r1.getInspections()) {
//			System.out.println(ins);
//		}
//		System.out.println(i1.getRestaurant());
	}
}
