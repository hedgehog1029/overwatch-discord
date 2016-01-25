package io.github.hedgehog1029.overwatch.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class NetConnection {
	public static String getData(String surl) throws IOException {
		URL url = new URL(surl);
		URLConnection connection = url.openConnection();
		connection.connect();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder result = new StringBuilder();

		while (true) {
			String line = reader.readLine();
			if (line == null) break;

			result.append(line);
		}

		return result.toString();
	}
}
