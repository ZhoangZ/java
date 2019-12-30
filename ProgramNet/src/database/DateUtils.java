package database;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class DateUtils {
	public static Date getDate(int day, int month, int year) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}
	public static Date getDate(String date, String delimeter) throws Exception {
		StringTokenizer token = new StringTokenizer(date, delimeter);
		String dd = token.nextToken();
		String mm = token.nextToken();
		String yy = token.nextToken();
		try {
			int d = Integer.parseInt(dd);
			if (d > 31) throw new IllegalDateException();
			int m = Integer.parseInt(mm);
			if (m > 12) throw new IllegalDateException();
			int y = Integer.parseInt(yy);
			if (y < 0) throw new IllegalDateException();
			return getDate(d, m, y);
		} catch (Exception e) {
			throw e;
		}
	}
	public static String toString(Date date) {
		return "19/09/2012";
	}
}
