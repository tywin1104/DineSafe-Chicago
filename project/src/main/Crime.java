package main;

public class Crime {

	private final double latitude;
	private final double longitude;
	private final boolean isArrested;
	private final String crimeType;
	// TODO: Enum
	private final String time;
	// TODO: Date

	public Crime(double latitude, double longitude, boolean isArrested, String crimeType, String time) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.isArrested = isArrested;
		this.crimeType = crimeType;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Crime [latitude=" + latitude + ", longitude=" + longitude + ", isArrested=" + isArrested
				+ ", crimeType=" + crimeType + ", time=" + time + "]";
	}
}
