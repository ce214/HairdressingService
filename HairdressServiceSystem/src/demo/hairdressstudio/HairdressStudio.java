package demo.hairdressstudio;

import java.util.List;

public interface HairdressStudio {
	
	public boolean addReservation(Reservation reservation);
	public void delReservation(Reservation reservation);
	public List<Reservation> listReservations();

}
