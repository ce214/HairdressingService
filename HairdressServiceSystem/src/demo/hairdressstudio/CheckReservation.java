package demo.hairdressstudio;

public class CheckReservation {
	
	public static int compareReservations(Reservation first, Reservation second) {
		return second.getInitialHour().compareTo(first.getInitialHour());
	}
	
}
