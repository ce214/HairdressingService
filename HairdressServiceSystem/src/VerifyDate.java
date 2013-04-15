import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

	public static boolean isThisHourTaken(String reserveDate, Date initHour,
			Date finHour, List<Reservation> list) {
		boolean flag = false;
		Iterator<Reservation> it = list.iterator();
		Reservation reserve;
		
		sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date beginDay = new Date();
		Date endDay = new Date();
		try {
			beginDay = sdf.parse(reserveDate + " 09:00");
			endDay = sdf.parse(reserveDate + " 18:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (initHour.before(beginDay) || finHour.after(endDay))
			flag = true;
		else {

			while (it.hasNext() && !flag) {
				reserve = it.next();
				if ((initHour.after(reserve.getInitialHour()) && initHour
						.before(reserve.getFinalHour()))
						|| initHour.equals(reserve.getInitialHour())) {
					flag = true;
				} else {
					if (finHour.after(reserve.getInitialHour())
							&& finHour.before(reserve.getFinalHour()))
						flag = true;
				}
			}
		}
		return flag;
	}

	public static Date add(Date date, int from, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(from, amount);
		return c.getTime();
	}
}
