package demo.hairdressstudio;

import java.util.Iterator;
import java.util.List;

public interface HairdressStudio {
	
	public boolean addReservation(Reservation reservation);
	public boolean delReservation(Reservation reservation);
	public List<Reservation> listReservations();
	public List<Reservation> getList();
}
