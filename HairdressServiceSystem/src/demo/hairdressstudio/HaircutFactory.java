package demo.hairdressstudio;

import java.util.Calendar;

public class HaircutFactory {

	public static Service constructManHaircut() {
		return new ManHaircut(30, 10);
	}

	public static Service constructWomanHaircut() {
		return new WomanHaircut(45, 15);
	}

	public static Reservation constructReservation(String name,
			String phoneNumber, Calendar initialHour, Service service) {
		return new Reservation(name, phoneNumber, initialHour, service);
	}
}
