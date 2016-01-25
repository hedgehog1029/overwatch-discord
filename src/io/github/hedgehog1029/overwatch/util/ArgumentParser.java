package io.github.hedgehog1029.overwatch.util;

import io.github.hedgehog1029.overwatch.util.args.ArgumentList;

import java.util.ArrayList;

public class ArgumentParser {
	public static ArgumentList parse(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (String arg : args) {
			sb.append(arg).append(" ");
		}

		if (sb.charAt(sb.length()) == ' ')
			sb.deleteCharAt(sb.length());

		return parse(sb.toString());
	}

	public static ArgumentList parse(String args) {
		if (args.length() == 0)
			return new ArgumentList(new String[0], args);

		ArrayList<String> parsed = new ArrayList<>();

		StringBuilder sb = new StringBuilder();
		boolean quoted = false;
		for (char c : args.toCharArray()) {
			if (c == ' ' && !quoted) {
				parsed.add(sb.toString());
				sb = new StringBuilder();
			} else if (c == '"') {
				quoted = !quoted;
			} else {
				sb.append(c);
			}
		}

		parsed.add(sb.toString()); // add the last stringbuilder

		return new ArgumentList(parsed, args);
	}
}
