package io.github.hedgehog1029.overwatch.util.args;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgumentList {

	private ArrayList<String> arguments = new ArrayList<>();
	private String original;

	public ArgumentList(String[] list, String original) {
		this.arguments.addAll(Arrays.asList(list));
		this.original = original;
	}

	public ArgumentList(List<String> list, String original) {
		this.arguments.addAll(list);
		this.original = original;
	}

	public boolean checkValid(int reqLength) {
		if (arguments.size() < reqLength) return false;
		else return true;
	}

	public ArrayList<String> getArguments() {
		return this.arguments;
	}

	public String get(int index) {
		try {
			return arguments.get(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public String getOriginal() {
		return this.original;
	}

	public String getFull() {
		return this.getFull(" ");
	}

	public String getFull(String spacer) {
		StringBuilder sb = new StringBuilder();

		for (String arg : arguments) {
			sb.append(arg).append(spacer);
		}

		return sb.toString();
	}

	public String getFrom(int from, String spacer) {
		if (from > arguments.size())
			return null;

		StringBuilder sb = new StringBuilder();

		for (String arg : arguments.subList(from, arguments.size())) {
			sb.append(arg).append(spacer);
		}

		return sb.toString();
	}

	public String getFrom(int from) {
		return this.getFrom(from, " ");
	}
}
