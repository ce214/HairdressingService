package demo.hairdressstudio;
import java.util.Calendar;

public class Reservation implements Comparable<Reservation> {
	private String name;
	private String phoneNumber;
	private Calendar initialHour;
	private Calendar finalHour;
	Service service;

	public Reservation(String name, String phoneNumber, Calendar initialHour,
			Calendar finalHour, Service service) {
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setInitialHour(initialHour);
		this.setFinalHour(finalHour);
		this.service = service;
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

	public Calendar getInitialHour() {
		return initialHour;
	}

	public void setInitialHour(Calendar initialHour) {
		this.initialHour = initialHour;
	}

	public Calendar getFinalHour() {
		return finalHour;
	}

	public void setFinalHour(Calendar finalHour) {
		this.finalHour = finalHour;
	}

	@Override
	public int compareTo(Reservation o) {
		return getInitialHour().compareTo(o.getInitialHour());
	}
}
