package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.Finder;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.OnlineStatus;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.entities.VoiceChannel;

import java.util.List;
import java.util.stream.Collectors;

@Description(usage = "random <channel> [iterations]", desc = "Generate a random number between 1 and people in a voice channel.")
public class Random implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{ "random", "r" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.TRUSTED;
	}

	@Override
	public void run(User sender, Guild origin, TextChannel channel, ArgumentList args) {
		java.util.Random random = new java.util.Random();

		if (args.get(0).equals("")) {
			ChatUtil.sendResponse(channel, sender, "Specify either a voice channel or 'here' for the current chat channel.");
			return;
		}

		if (args.get(0).equalsIgnoreCase("here")) {
			List<User> online = channel.getUsers().parallelStream().filter(user -> user.getOnlineStatus().equals(OnlineStatus.ONLINE)).collect(Collectors.toList());

			User result = online.get(random.nextInt(online.size()) + 1);

			ChatUtil.sendResponse(channel, sender, String.format("Randomly picked <@%s> as winner!", result.getId()));
			return;
		}

		VoiceChannel target;
		if (args.get(0).equalsIgnoreCase("mine"))
			target = Finder.findVoiceChannelOfUser(sender, origin);
		else
		    target = Finder.findVc(origin, args.get(0));

		if (target == null) {
			ChatUtil.sendResponse(channel, sender, String.format("I couldn't find the voice channel %s. :(", args.get(0)));
			return;
		}

		if (target.getUsers().size() < 1) {
			ChatUtil.sendResponse(channel, sender, String.format("There's nobody in %s!", args.get(0)));
			return;
		}

		int iterations = 1;

		try {
			iterations = Integer.parseInt(args.get(1));
		} catch (NumberFormatException ignored) {
		}

		for (int i = 0; i < iterations; i++) {
			User result = target.getUsers().get(random.nextInt(target.getUsers().size()));

			ChatUtil.sendResponse(channel, sender, String.format("Randomly picked <@%s> as victim!", result.getId()));
		}
	}
}
