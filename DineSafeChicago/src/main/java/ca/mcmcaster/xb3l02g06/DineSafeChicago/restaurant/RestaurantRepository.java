package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
	public Restaurant findByLicenseNum(int licenseNum);
}
