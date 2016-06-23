package io.github.hedgehog1029.overwatch.util;

import java.util.HashMap;

public class Table<T> {

	private HashMap<T, T> map = new HashMap<>();

	public Table(T... b) {
		if (b.length % 2 != 0) {
			throw new IllegalArgumentException("Arguments must be in pairs!");
		}

		for (int i = 0; i < b.length; i = i + 2) {
			map.put(b[i], b[i + 1]);
		}
	}

	public T get(T key) {
		return map.get(key);
	}
}
