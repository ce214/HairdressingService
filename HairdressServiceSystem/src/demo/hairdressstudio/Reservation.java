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

	public Reservation() {
		// TODO Auto-generated constructor stub
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
	
	public Service getService() {
		return service;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public int compareTo(Reservation o) {
		return CheckReservation.compareReservations(o, this);
	}
}
