package demo.hairdressstudio.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import demo.hairdressstudio.HairdressStudio;
import demo.hairdressstudio.Reservation;
import demo.hairdressstudio.database.MySQLAccess;

public class HairdressStudioImpl implements HairdressStudio {

	private List<Reservation> list;
	MySQLAccess db;

	public HairdressStudioImpl() {
		db = new MySQLAccess();
		list = db.getListFromDataBase();
	}

	@Override
	public boolean addReservation(Reservation reservation) {
		if (isValid(reservation)) {
			db.addReservation(reservation);
			list.add(reservation);
			Collections.sort(list);
			return true;
		} else {
			return false;
		}
	}

	private boolean isValid(Reservation reservation) {
		return ReservationValidator.isReservationOK(reservation, list);
	}

	@Override
	public List<Reservation> listReservations() {
		List<Reservation> threeDayList = new ArrayList<Reservation>();
		Collections.sort(list);
		Calendar curr = Calendar.getInstance();

		for (int i = 0; i < 4; i++) {
			Iterator<Reservation> it = list.iterator();
			while (it.hasNext()) {
				Reservation reserve = it.next();

				if (reserve.getInitialHour().get(Calendar.DATE) == curr
						.get(Calendar.DATE)) {
					threeDayList.add(reserve);
				}
			}
			curr.add(Calendar.DAY_OF_WEEK, 1);
		}
		return threeDayList;
	}

	public List<Reservation> getList() {
		return list;
	}

	@Override
	public boolean delReservation(Reservation reservation) {
		if (db.delReservationFromDataBase(reservation)
				& list.remove(reservation))
			return true;
		else
			return false;
	}
}
