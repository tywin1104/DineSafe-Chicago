package main;

public class Inspection {
	private final String result;
	// TODO: Enum
	private final String violation;
	private final String time;
	// TODO: Parse As Date

	
	@Override
	public String toString() {
		return "Inspection [result=" + result + ", time=" + time + "]";
	}

	public Inspection(String result, String violation, String time) {
		super();
		this.result = result;
		this.violation = violation;
		this.time = time;
	}

	public String getResult() {
		return result;
	}

	public String getViolation() {
		return violation;
	}

	public String getTime() {
		return time;
	}

}
