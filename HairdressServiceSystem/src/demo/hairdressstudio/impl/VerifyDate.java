package demo.hairdressstudio.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import demo.hairdressstudio.Reservation;

public class VerifyDate {

	static SimpleDateFormat sdf = new SimpleDateFormat();

	public static boolean isThisDateValid(String dateToValidate) {

		if (dateToValidate == null) {
			return false;
		}

		sdf = new SimpleDateFormat("dd/MM/yy");
		sdf.setLenient(false);

		try {
			sdf.parse(dateToValidate);
			return true;
		} catch (ParseException e) {
			System.out.println("Greshna data");
			return false;
		}
	}

	public static boolean isThisHourValid(String hour) {

		if (hour == null) {
			return false;
		}

		sdf = new SimpleDateFormat("HH:mm");
		sdf.setLenient(false);

		try {
			sdf.parse(hour);
			return true;
		} catch (ParseException e) {
			System.out.println("Greshen chas!");
			return false;
		}
	}

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
