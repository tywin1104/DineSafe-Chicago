package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
	public Restaurant findByLicenseNum(int licenseNum);
	public Restaurant findByRestaurantIdentity(RestaurantIdentity restaurantIdentity);
	public List<Restaurant> findByClosedFalse();
	public List<Restaurant> findByClosed(boolean closed);
}
