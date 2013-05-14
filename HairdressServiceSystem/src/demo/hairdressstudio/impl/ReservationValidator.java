package demo.hairdressstudio.impl;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import demo.hairdressstudio.Reservation;

public class ReservationValidator {

	public static boolean isReservationOK(Reservation res,
			List<Reservation> list) {
		Calendar finHour = calculateFinHour(res.getInitialHour(), res
				.getService().getDuration());

		boolean isOK = true;
		Iterator<Reservation> it = list.iterator();

		Reservation reserve;
		if ((res.getInitialHour().get(Calendar.HOUR_OF_DAY) < 9)
				|| (finHour.get(Calendar.HOUR_OF_DAY) >= 18 && finHour
						.get(Calendar.MINUTE) > 0))
			isOK = false;
		else {

			while (it.hasNext() && isOK) {
				reserve = it.next();
				if ((res.getInitialHour().after(reserve.getInitialHour()) & res
						.getInitialHour().before(
								calculateFinHour(reserve.getInitialHour(),
										reserve.getService().getDuration())))
						|| res.getInitialHour()
								.equals(reserve.getInitialHour())) {
					isOK = false;
				} else {
					if (finHour.after(reserve.getInitialHour())
							& finHour.before(calculateFinHour(reserve
									.getInitialHour(), reserve.getService()
									.getDuration())))
						isOK = false;
					if (finHour.equals(reserve.getInitialHour()))
						isOK = true;
				}
			}
		}
		return isOK;
	}

	private static Calendar calculateFinHour(Calendar initialHour, long duration) {
		Calendar finalHour = Calendar.getInstance();
		finalHour.set(initialHour.get(Calendar.YEAR),
				initialHour.get(Calendar.MONTH),
				initialHour.get(Calendar.DAY_OF_MONTH),
				initialHour.get(Calendar.HOUR_OF_DAY),
				initialHour.get(Calendar.MINUTE),
				initialHour.get(Calendar.SECOND));
		finalHour.add(Calendar.MINUTE, (int) duration);
		return finalHour;
	}
}
