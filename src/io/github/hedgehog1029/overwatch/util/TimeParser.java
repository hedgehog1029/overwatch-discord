package io.github.hedgehog1029.overwatch.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeParser {
	public static Calendar now() {
		return Calendar.getInstance();
	}

	public static Calendar future(int type, int future) {
		Calendar now = now();
		now.add(type, future);

		return now;
	}

	public static Calendar future(String input) {
		return future(parseTimeUnit(input), parse(input));
	}

	public static int parseTimeUnit(String input) {
		Pattern p = Pattern.compile("(\\d+)(\\w[^\\d]{1})");
		Matcher m = p.matcher(input);

		return extract(m.group(2));
	}

	public static int parse(String input) {
		Pattern p = Pattern.compile("(\\d+)(\\w[^\\d]{1})");
		Matcher m = p.matcher(input);

		return Integer.valueOf(m.group(1));
	}

	public static int extract(String input) {
		switch (input.toLowerCase()) {
			case "s":
				return Calendar.SECOND;
			case "m":
				return Calendar.MINUTE;
			case "h":
				return Calendar.HOUR;
			case "d":
				return Calendar.DAY_OF_WEEK;
			default:
				return Calendar.MINUTE;
		}
	}
}
