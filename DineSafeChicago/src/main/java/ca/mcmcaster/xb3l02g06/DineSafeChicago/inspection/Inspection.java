package ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import net.bytebuddy.asm.Advice.This;

/**
 * Represents an food inspection record for a restaurnt.
 * 
 * @author Tianyi Zhang
 */
@Entity
public class Inspection {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "restaurant_name", referencedColumnName = "restaurant_name"),
			@JoinColumn(name = "restaurant_address", referencedColumnName = "restaurant_address") })
	private Restaurant restaurant;

	private String result;
	@Lob
	private String violation;
	private Date time;

	/**
	 * Get the string representation of this inspection
	 * 
	 * @return the string representation of this inspection
	 */
	@Override
	public String toString() {
		return "Inspection [result=" + result + ", time=" + time + "]";
	}

	protected Inspection() {
	}

	/**
	 * Constructor for an inspection with the specified field values.
	 * 
	 * @param result    the result of this inspection
	 * @param violation the violation string recorded by the inspector
	 * @param timeStr   string format of the inspection time DD/MM/YYYY
	 */
	public Inspection(String result, String violation, String timeStr) {
		this.result = result;
		this.violation = violation;
		this.time = parseTime(timeStr);
	}

	/**
	 * Get the restaurant associated with this inspection
	 * 
	 * @return the restaurant associated with this inspection
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Set the restaurant associated with this inspection
	 * 
	 * @param the restaurant associated with this inspection
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * Get the result of this inspection
	 * 
	 * @return the result of this inspection
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Get the violation of this inspection
	 * 
	 * @return the violation of this inspection
	 */
	public String getViolation() {
		return violation;
	}

	/**
	 * Get the time of this inspection
	 * 
	 * @return the time of this inspection
	 */
	public Date getTime() {
		return time;
	}

	private Date parseTime(String dateStr) {
		SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
		Date time = null;
		try {
			time = parser.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("Unparseable using " + dateStr);
		}
		return time;
	}

	// For calculating food safety score
	private int getViolationNumber() {
		if (this.violation.isEmpty()) {
			return 0;
		}
		String[] splitViolationStr = this.violation.trim().split("\\.");
		return Integer.parseInt(splitViolationStr[0]);
	}

	/**
	 * Calculate the score for this inspection
	 * 
	 * @return the score for this inspection
	 */
	public int CalcInspectionScore() {
		int inspectionScore = 0;
		if (this.getResult().equals("Pass")) {
			inspectionScore = 100;
		} else if (this.getResult().equals("Pass w/ Conditions")) {
			int condition = this.getViolationNumber();
			if (condition <= 14) {
				inspectionScore = 50;
			} else if (condition <= 29) {
				inspectionScore = 70;
			} else {
				inspectionScore = 90;
			}
		}
		return inspectionScore;
	}
}
