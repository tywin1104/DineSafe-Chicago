package ca.mcmcaster.xb3l02g06.DineSafeChicago.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantRepository;

@ComponentScan(value = {"ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant"})
@SpringBootApplication
public class DataLoader extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private RestaurantRepository restaurantRepo;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DataLoader.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("HAHA");
	}
}

//public class DataLoader implements CommandLineRunner {
//
//	@Autowired
//	private RestaurantRepository restaurantRepo;
//
//	public static void main(String[] args) throws Exception {
//
//		SpringApplication.run(DataLoader.class, args);
//
//	} 
//
//	@Override
//	public void run(String... args) throws Exception {
//		contextLoads();
//	}
//
//	public void contextLoads() throws FileNotFoundException {
//
//		String fileIn = "/Users/tianyizhang/Desktop/McMaster/CS2XB3/DineSafe-Chicago/DineSafeChicago/src/main/resources/data/InspectionsSample.csv";
//		String line = null;
//
//		CSVReader reader = new CSVReader(new FileReader(fileIn), ',', '"', 1);
//		List<String[]> allRows;
//		try {
//			allRows = reader.readAll();
//			for (String[] temp : allRows) {
//				String name = temp[1];
//				int licenseNum = Integer.parseInt(temp[2]);
//				String address = temp[4];
//				int zip = Integer.parseInt(temp[5]);
//				String dateStr = temp[6];
//				String result = temp[7];
//				String violation = temp[8];
//				double latitude = Double.parseDouble(temp[9]);
//				double longitude = Double.parseDouble(temp[10]);
//
//				Restaurant restaurant = restaurantRepo.findByLicenseNum(licenseNum);
//				if (restaurant == null) {
//					restaurant = new Restaurant(name, zip, latitude, longitude, licenseNum, address);
//				} 
//				Inspection inspection = new Inspection(result, violation, dateStr);
//				restaurant.addInspection(inspection);
//				restaurantRepo.save(restaurant);
//			}
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}