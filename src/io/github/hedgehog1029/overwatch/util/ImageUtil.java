package io.github.hedgehog1029.overwatch.util;

import io.github.hedgehog1029.overwatch.mod.EmoteManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageUtil {
	public static void downloadImage(EmoteManager.Emote emote, URL url) {
		try {
			BufferedImage image = ImageIO.read(url);

			ImageIO.write(image, "png", emote.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
