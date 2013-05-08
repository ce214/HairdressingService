package demo.hairdressstudio.impl;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import demo.hairdressstudio.Reservation;

public class ReservationValidator {

	public static boolean isReservationOK(Calendar initHour, Calendar finHour,
			List<Reservation> list) {

		boolean isOK = true;
		Iterator<Reservation> it = list.iterator();

		Reservation reserve;
		if ((initHour.get(Calendar.HOUR_OF_DAY) < 9)
				|| (finHour.get(Calendar.HOUR_OF_DAY) >= 18 && finHour
						.get(Calendar.MINUTE) > 0))
			isOK = false;
		else {

			while (it.hasNext() && isOK) {
				reserve = it.next();
				if ((initHour.after(reserve.getInitialHour()) && initHour
						.before(reserve.getFinalHour()))
						|| initHour.equals(reserve.getInitialHour())) {
					isOK = false;
				} else {
					if (finHour.after(reserve.getInitialHour())
							&& finHour.before(reserve.getFinalHour()))
						isOK = false;
				}
			}
		}
		return isOK;
	}

	public static boolean isReservationOK(Reservation reservation,
			List<Reservation> list) {
		return isReservationOK(reservation.getInitialHour(),
				reservation.getFinalHour(), list);
	}
}
