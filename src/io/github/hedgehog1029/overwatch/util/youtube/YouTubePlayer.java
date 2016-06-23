package io.github.hedgehog1029.overwatch.util.youtube;

import net.dv8tion.jda.JDA;
import net.dv8tion.jda.audio.player.URLPlayer;

public class YouTubePlayer extends URLPlayer {

	private String videoId;

	public YouTubePlayer(JDA api, String videoCode) {
		super(api);

		this.videoId = videoCode;
	}

	public void resolve() {

	}
}
