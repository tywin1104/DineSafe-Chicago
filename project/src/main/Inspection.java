package main;

import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.Date;

public class Inspection {
	enum ResultT {
		PASS, CONDITIONALPASS, FAIL, INVALID, CLOSED; 
	}
	
	private ResultT result;
	private final String violation;
	private final Date time;

	@Override
	public String toString() {
		return "Inspection [result=" + result + ", time=" + time + "]";
	}

	public Inspection(ResultT result, String violation, String timeStr) {
		this.result = result;
		this.violation = violation;
		this.time = parseTime(timeStr);
	}

	public ResultT getResult() {
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
		try {
			Date time = parser.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("Unparseable using " + dateStr);
		}
		return time;
	}
	
	private int getViolationNumber() {
		String[] splitViolationStr = this.violation.trim().split("\\s+");
		return Integer.parseInt(splitViolationStr[0]);
	}
	
	public int CalcInspectionScore() {
		int inspectionScore = 0;
		if(this.getResult() == ResultT.PASS) {
			inspectionScore = 100;
		}
		else if(this.getResult() == ResultT.CONDITIONALPASS) {
			int condition = this.getViolationNumber();
			if(condition <= 14) {
				inspectionScore = 50;
			}
			else if(condition <= 29) {
				inspectionScore = 70;
			}
			else {
				inspectionScore = 90;
			}
		}
		return inspectionScore;
	}

}