
public class Reservation {
	private String name;
	private String phoneNumber;
	private String reserveDate;

	private enum Service {
		manHaircut, womanHaircut
	}

	public Reservation(String name, String phoneNumber, String reserveDate) {
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setReserveDate(reserveDate);
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

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
}
