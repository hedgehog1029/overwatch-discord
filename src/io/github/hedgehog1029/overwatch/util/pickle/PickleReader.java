package io.github.hedgehog1029.overwatch.util.pickle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class PickleReader {

	private FileReader in;

	public PickleReader(FileReader in) {
		this.in = in;
	}

	public JSONObject read() {
		try {
			return (JSONObject) new JSONParser().parse(in);
		} catch (IOException e) {
			System.out.println("[pickle WARN] Couldn't create an input stream!");
		} catch (ParseException e) {
			System.out.println("[pickle WARN] Couldn't parse file as JSON!");
		}

		return null;
	}

	public void end() {
		try {
			in.close();
		} catch (IOException e) {
			System.out.println("[pickle WARN] Couldn't close the input stream!");
		}
	}
}
