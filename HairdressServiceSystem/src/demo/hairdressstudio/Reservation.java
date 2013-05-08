package demo.hairdressstudio;
import java.util.Calendar;

public class Reservation implements Comparable<Reservation> {
	private String name;
	private String phoneNumber;
	private Calendar initialHour;
	Service service;

	public Reservation(String name, String phoneNumber, Calendar initialHour, Service service) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.initialHour = initialHour;
		this.service = service;
	}

	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Calendar getInitialHour() {
		return initialHour;
	}
	
	public Service getService() {
		return service;
	}
	
	public int compareTo(Reservation o) {
		return CheckReservation.compareReservations(o, this);
	}
}
