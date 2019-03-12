package ca.mcmcaster.xb3l02g06.DineSafeChicago;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	public void contextLoads() {
		Restaurant r1 = new Restaurant("asd", 100, 12.2, 12.2);
		Inspection i1 = new Inspection("pass", "no comment", "11/04/1998");
//		System.out.println(r1);
//		System.out.println(i1);
		Inspection i2 = new Inspection("pass with condition", "no comment", "11/04/2019");
		r1.addInspection(i1);
		r1.addInspection(i2);
		restaurantRepo.save(r1); 
//		for(Inspection ins: r1.getInspections()) {
//			System.out.println(ins);
//		}
		System.out.println(i1.getRestaurant());
	}
}
