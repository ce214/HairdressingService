package demo.hairdressstudio.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import demo.hairdressstudio.HairdressStudio;
import demo.hairdressstudio.Reservation;

public class HairdressStudioImpl implements HairdressStudio {

	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
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
	public List<Reservation> listReservations() {
		List<Reservation> threeDayList = new ArrayList<Reservation>();
		Collections.sort(list);
		Calendar curr = Calendar.getInstance();

		for (int i = 0; i < 4; i++) {
			Iterator<Reservation> it = list.iterator();
			while (it.hasNext()) {
				Reservation reserve = it.next();

				if (reserve.getInitialHour().get(Calendar.DATE) == curr.get(Calendar.DATE)) {
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
		return list.remove(reservation);
	}
}
