package demo.hairdressstudio.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserInputValidation {
	
	static SimpleDateFormat sdf = new SimpleDateFormat();

	static boolean isThisDateValid(String dateToValidate) {

		if (dateToValidate == null) {
			return false;
		}

		sdf = new SimpleDateFormat("yy/MM/dd");
		sdf.setLenient(false);

		try {
			sdf.parse(dateToValidate);
			return true;
		} catch (ParseException e) {
			System.out.println("Greshna data");
			return false;
		}
	}

	static boolean isThisHourValid(String hour) {

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

}
