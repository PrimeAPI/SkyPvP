package de.primeapi.primeplugins.skypvp.util;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	public static String getDateFromUnix(Long unix) {
		try {
			Date date = new Date(unix);
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			return df.format(date);
		} catch (Exception ex) {
			return null;
		}
	}

	public static String unixToRemaining(long l) {
		return unixToRemaining(l, false);
	}

	public static String unixToRemaining(long l, boolean displaySeconds) {
		if (l == -1) return "Niemals";
		String msg = "";

		long now = System.currentTimeMillis();
		long diff = l - now;
		long seconds = (diff / 1000);

		if (seconds >= 60 * 60 * 24) {
			long days = seconds / (60 * 60 * 24);
			seconds = seconds % (60 * 60 * 24);
			msg += days + " Tag(e) ";

		}
		if (seconds >= 60 * 60) {
			long h = seconds / (60 * 60);
			seconds = seconds % (60 * 60);
			msg += h + " Stunde(n) ";
		}
		if (seconds >= 60) {
			long min = seconds / 60;
			seconds = seconds % 60;
			msg += min + " Minute(n) ";
		}
		if (displaySeconds) {
			msg += seconds + " Sekunde(n) ";
		}

		return msg;
	}

	public static String unixToRemainingFromDoff(long l) {
		return unixToRemainingFromDoff(l, false);
	}

	public static String unixToRemainingFromDoff(long l, boolean displaySeconds) {
		if (l == -1) return "Permanent";
		String msg = "";

		long seconds = (l / 1000);

		if (seconds >= 60 * 60 * 24) {
			long days = seconds / (60 * 60 * 24);
			seconds = seconds % (60 * 60 * 24);
			msg += days + " Tag(e) ";

		}
		if (seconds >= 60 * 60) {
			long h = seconds / (60 * 60);
			seconds = seconds % (60 * 60);
			msg += h + " Stunde(n) ";
		}
		if (seconds >= 60) {
			long min = seconds / 60;
			seconds = seconds % 60;
			msg += min + " Minute(n) ";
		}
		if (displaySeconds) {
			msg += seconds + " Sekunde(n) ";
		}

		return msg;
	}

	public static Long getTimeFromString(String s) {
		s = s.toLowerCase();
		if (s.equalsIgnoreCase("P") || s.equalsIgnoreCase("PERMANENT")) {
			return -1L;
		}
		int value = 0;
		String valueString = s
				.replaceAll("w", "")
				.replaceAll("d", "")
				.replaceAll("h", "")
				.replaceAll("m", "")
				.replaceAll("s", "");
		try {
			value = Integer.parseInt(valueString);
		} catch (Exception ex) {
			return null;
		}
		String type = s.replaceAll(String.valueOf(value), "");
		switch (type.toLowerCase()) {
			case "w":
				return toMillies(value * 7, 0, 0, 0);
			case "d":
				return toMillies(value, 0, 0, 0);
			case "h":
				return toMillies(0, value, 0, 0);
			case "m":
				return toMillies(0, 0, value, 0);
			case "s":
				return toMillies(0, 0, 0, value);
			default:
				return null;
		}
	}

	public static Long toMillies(int days, int hours, int mins, int sec) {
		Long min = (long) mins * 60;
		Long hour = (long) hours * (60 * 60);
		Long day = (long) days * (60 * 60 * 24);
		long time = min + hour + day + sec;
		time = time * 1000;
		return time;

	}

}
