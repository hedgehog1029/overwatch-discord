package io.github.hedgehog1029.overwatch.util;

import java.io.IOException;

public class YouTubeUtil {
	public static void downloadYT(String url) {
		try {
			Process p = Runtime.getRuntime().exec("youtube-dl -o \"videos/%(id)s.%(ext)s\" " + url);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
