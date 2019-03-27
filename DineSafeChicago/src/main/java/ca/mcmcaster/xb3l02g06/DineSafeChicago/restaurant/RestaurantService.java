package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection.InspectionRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private InspectionRepository inspectionRepo;

	@Autowired
	private RestaurantRepository restaurantRepo;
	
	

}
