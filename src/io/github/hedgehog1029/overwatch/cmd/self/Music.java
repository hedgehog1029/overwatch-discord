package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.Finder;
import io.github.hedgehog1029.overwatch.util.Table;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.audio.player.FilePlayer;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.entities.VoiceChannel;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Description(usage = "jukebox <voicechannel> <song>", desc = "Play sick tunes!")
public class Music implements Command {

	private Table<String> musicList = new Table<>(
			"localforecast", "music/localforecast.mp3",
			"necrofantasia", "music/necro.mp3",
			"tank", "music/tank.mp3",
			"gunsnroses", "music/baccano-gunsnroses.mp3",
			"jazz", "music/jazz.mp3",
			"redlikeroses", "music/redlikeroses.mp3",
			"oneinchpunch", "music/oneinchpunch.mp3"
	);

	private FilePlayer player = null;

	@Override
	public String[] getAliases() {
		return new String[]{ "jukebox", "play" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.TRUSTED;
	}

	@Override
	public void run(User sender, Guild origin, TextChannel channel, ArgumentList args) {
		switch (args.get(0)) {
			case "pause":
				if (player == null) {
					ChatUtil.sendResponse(channel, sender, "You need to play a song before you can pause!");
					return;
				}
				if (player.isPaused()) {
					ChatUtil.sendResponse(channel, sender, "Already paused!");
					return;
				}

				player.pause();
				ChatUtil.sendResponse(channel, sender, "Paused playback.");
				return;
			case "stop":
				if (player == null) {
					ChatUtil.sendResponse(channel, sender, "You need to play a song before you can stop it!");
					return;
				}
				if (player.isStopped()) {
					ChatUtil.sendResponse(channel, sender, "Already stopped!");
					return;
				}

				player.stop();
				channel.getJDA().getAudioManager().closeAudioConnection();
				player = null;
				ChatUtil.sendResponse(channel, sender, "Stopped playback.");
				return;
			case "resume":
				if (player == null || player.isStopped()) {
					ChatUtil.sendResponse(channel, sender, "You can only resume a paused song!");
					return;
				}
				if (player.isPlaying()) {
					ChatUtil.sendResponse(channel, sender, "Already playing!");
					return;
				}

				player.play();
				ChatUtil.sendResponse(channel, sender, "Resumed playback.");
				return;
			case "leave":
				if (player == null) {
					ChatUtil.sendResponse(channel, sender, "Already closed connection!");
					return;
				}

				ChatUtil.sendResponse(channel, sender, "Left " + channel.getJDA().getAudioManager().getConnectedChannel().getName());
				channel.getJDA().getAudioManager().closeAudioConnection();
				player = null;
				return;
			default:
				break;
		}

		if (args.get(0) == null || args.get(1) == null) {
			ChatUtil.sendResponse(channel, sender, "I need a voice channel and a song to play!");
			return;
		}

		VoiceChannel target;

		if (args.get(0).equalsIgnoreCase("mine")) {
			target = Finder.findVoiceChannelOfUser(sender, origin);
		} else {
			target = Finder.findVc(origin, args.get(0));
		}

		String targetSong = musicList.get(args.get(1));

		if (targetSong == null) {
			ChatUtil.sendResponse(channel, sender, "I don't have that song :(");
			return;
		}

		try {
			player = new FilePlayer(new File(targetSong));
		} catch (IOException | UnsupportedAudioFileException e) {
			ChatUtil.sendResponse(channel, sender, "I couldn't play music, for some reason. :(");
			return;
		}

		if (!channel.getJDA().getAudioManager().isConnected())
			channel.getJDA().getAudioManager().openAudioConnection(target);

		channel.getJDA().getAudioManager().setSendingHandler(player);

		player.setVolume(0.3F);
		player.play();

		ChatUtil.sendResponse(channel, sender, String.format("Now playing %s in %s.", args.get(1), target.getName()));
	}
}
