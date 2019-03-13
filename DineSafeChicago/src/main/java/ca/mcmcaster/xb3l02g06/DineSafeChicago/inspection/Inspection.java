package ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;

@Entity
public class Inspection {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;

	private String result;
	@Column(length=8192)
	private String violation;
	private Date time;
	
	@Override
	public String toString() {
		return "Inspection [result=" + result + ", time=" + time + "]";
	} 

	protected Inspection() {
	}

	public Inspection(String result, String violation, String timeStr) {
		this.result = result;
		this.violation = violation;
		this.time = parseTime(timeStr);
	} 
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getResult() {
		return result;
	}

	public String getViolation() {
		return violation;
	}

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
}
