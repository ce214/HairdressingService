package demo.hairdressstudio.impl;

import java.util.ArrayList;
import java.util.List;

import demo.hairdressstudio.HairdressStudio;
import demo.hairdressstudio.Reservation;

public class HairdressStudioImpl implements HairdressStudio {

	private List<Reservation> list;

	public HairdressStudioImpl() {
		list = new ArrayList<Reservation>();
	}

	@Override
	public boolean addReservation(Reservation reservation) {
		if (isValid(reservation)) {
			list.add(reservation);
			return true;
		} else {
			return false;
		}
	}

	private boolean isValid(Reservation reservation) {
		return VerifyDate.isReservationOK(reservation, list);
	}

	@Override
	public void delReservation(Reservation reservation) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reservation> listReservations() {
		// TODO Auto-generated method stub
		return null;
	}

}
