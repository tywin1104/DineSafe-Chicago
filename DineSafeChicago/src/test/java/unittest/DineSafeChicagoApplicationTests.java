package unittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.InspectionRepository;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class DineSafeChicagoApplicationTests {

	@Autowired
	private InspectionRepository inspectionRepo;

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Test
	public void contextLoads() { 
		
	}
}
