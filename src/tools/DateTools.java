package tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * This class contains static functions which return data.
 * @author Sadi Wali
 *
 */
public class DateTools {
	
	/**
	 * Get today's date formatted : [mm/dd/yyyy]
	 * @return today's formatted date.
	 */
	public static String getFormattedDate() {
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		LocalDate today = LocalDate
		return dateFormat.format(today);
	}

}
