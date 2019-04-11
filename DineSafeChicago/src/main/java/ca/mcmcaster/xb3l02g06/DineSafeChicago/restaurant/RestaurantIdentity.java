package ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Class that encapsulate the restaurant name and address for composite key
 * purpose
 * 
 * @author Tianyi Zhang
 */
@Embeddable
public class RestaurantIdentity implements Serializable {

	@Column(name = "restaurant_address")
	private String address;

	@Column(name = "restaurant_name")
	private String name;

	public RestaurantIdentity() {

	}

	/**
	 * Constructor for a restaurant identity entity with the specified field values.
	 * 
	 * @param address the address of the restaurant
	 * @param name    the name of the restaurant
	 */
	public RestaurantIdentity(@NotNull String address, @NotNull String name) {
		super();
		this.address = address;
		this.name = name;
	}

	/**
	 * Get the address for this restaurant
	 * 
	 * @return the address for this restaurant
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address for this restaurant
	 * 
	 * @param the address for this restaurant
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the name for this restaurant
	 * 
	 * @return the name for this restaurant
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the address for this restaurant
	 * 
	 * @param the address for this restaurant
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the hashcode for this entity
	 * 
	 * @return the hashcode for this entity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Check the equality between two entities
	 * 
	 * @param obj the other entity to be compared with the current one
	 * @return if the current object is equal to another object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantIdentity other = (RestaurantIdentity) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
