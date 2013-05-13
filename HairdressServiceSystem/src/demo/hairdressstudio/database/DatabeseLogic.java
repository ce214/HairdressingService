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
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, time.getHours());
		cal.set(Calendar.MINUTE, time.getMinutes());
		cal.set(Calendar.SECOND, 0);
		
		return cal;
	}

	public static Service getService(Long duration) {
		if (duration == 30)
			return HaircutFactory.constructManHaircut();
		else
			return HaircutFactory.constructWomanHaircut();
	}
}
