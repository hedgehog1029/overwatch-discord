package io.github.hedgehog1029.overwatch.util;

import io.github.hedgehog1029.overwatch.util.pickle.PickleReader;
import io.github.hedgehog1029.overwatch.util.pickle.PickleWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Pickle {

	private String file;

	public static Pickle open(String file) {
		return new Pickle(file);
	}

	private Pickle(String file) {
		this.file = file;
	}

	private PickleWriter writer;
	private PickleReader reader;

	public PickleWriter writer() {
		if (writer != null) return writer;

		try {
			this.writer = new PickleWriter(new FileWriter(file));

			return writer;
		} catch (IOException e) {
			System.out.println("[pickle WARN] Couldn't open that file!");

			return null;
		}
	}

	public PickleReader reader() {
		if (reader != null) return reader;

		try {
			this.reader = new PickleReader(new FileReader(file));

			return reader;
		} catch (IOException e) {
			System.out.println("[pickle WARN] Couldn't open that file!");
		}

		return null;
	}
}
