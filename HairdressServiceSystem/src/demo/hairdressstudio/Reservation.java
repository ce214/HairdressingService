package demo.hairdressstudio;
import java.util.Calendar;
import java.util.Date;

public class Reservation implements Comparable<Reservation> {
	private String name;
	private String phoneNumber;
	private Date initialHour;
	private Date finalHour;

	public Reservation(String name, String phoneNumber, Date initialHour,
			Date finalHour) {
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setInitialHour(initialHour);
		this.setFinalHour(finalHour);
	}
	
	public Calendar getDate(){
		Calendar c = Calendar.getInstance();
		c.setTime(initialHour);
		return c;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getInitialHour() {
		return initialHour;
	}

	public void setInitialHour(Date initialHour) {
		this.initialHour = initialHour;
	}

	public Date getFinalHour() {
		return finalHour;
	}

	public void setFinalHour(Date finalHour) {
		this.finalHour = finalHour;
	}

	@Override
	public int compareTo(Reservation o) {
		return getInitialHour().compareTo(o.getInitialHour());
	}
}
