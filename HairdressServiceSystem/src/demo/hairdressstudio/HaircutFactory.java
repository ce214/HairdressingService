package demo.hairdressstudio;

import java.util.Calendar;

public class HaircutFactory {

	public static Service constructManHaircut(String name) {
		return new ManHaircut(name, 30, 10);
	}

	public static Service constructWomanHaircut(String name) {
		return new WomanHaircut(name, 45, 15);
	}

	public static Reservation constructReservation(String name,
			String phoneNumber, Calendar initialHour, Service service) {
		return new Reservation(name, phoneNumber, initialHour, service);
	}
}
