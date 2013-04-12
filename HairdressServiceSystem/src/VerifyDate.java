import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VerifyDate {
	
	public static boolean isThisDateValid(String dateToValidate, String dateFromat) {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {
			Date date = sdf.parse(dateToValidate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
