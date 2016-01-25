package io.github.hedgehog1029.overwatch.util.pickle;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class PickleWriter {

	private FileWriter out;

	public PickleWriter(FileWriter os) {
		this.out = os;
	}

	public PickleWriter write(JSONObject object) {
		try {
			out.write(object.toJSONString());
		} catch (IOException e) {
			System.out.println("[pickle WARN] Couldn't write data! :(");
		}

		return this;
	}

	public void end() {
		try {
			out.close();
		} catch (IOException e) {
			System.out.println("[pickle WARN] Couldn't close the output stream!");
		}
	}
}
