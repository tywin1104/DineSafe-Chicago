package main;

public class Restaurant implements Comparable<Restaurant> {
	private final String name;
	private final int id;
	private final int zip;
	private final double latitude;
	private final double longitude;
	private Inspection[] inspections;
	private Crime[] crimes;
	private double foodSafetyScore;
	private double neighborhoodSafetyScore;
	private double overallScore;

	public Restaurant(String name, int id, int zip, double latitude, double longitude) {
		super();
		this.name = name;
		this.id = id;
		this.zip = zip;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getZip() {
		return zip;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getNeighborhoodSafetyScore() {
		return neighborhoodSafetyScore;
	}

	public double getOverallScore() {
		return overallScore;
	}

	public int compareTo(Restaurant other) {
		return Double.compare(overallScore, other.overallScore);
	}
	
	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", overallScore=" + overallScore + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + zip;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (zip != other.zip)
			return false;
		return true;
	}
}
