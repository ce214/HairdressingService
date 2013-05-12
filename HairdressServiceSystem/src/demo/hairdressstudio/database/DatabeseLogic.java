package demo.hairdressstudio.database;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import demo.hairdressstudio.HaircutFactory;
import demo.hairdressstudio.Service;

public class DatabeseLogic {

	@SuppressWarnings("deprecation")
	public static Calendar getCalendarDate(Date date, Time time) {
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth(), date.getDate(),
				time.getHours(), time.getMinutes(), 00);

		return cal;
	}

	public static Service getService(Long duration) {
		if (duration == 30)
			return HaircutFactory.constructManHaircut();
		else
			return HaircutFactory.constructWomanHaircut();
	}
}
