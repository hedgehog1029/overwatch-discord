package io.github.hedgehog1029.overwatch.cmd.self;


import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.mod.EmoteManager;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

import java.net.MalformedURLException;
import java.net.URL;

@Description(usage = "emote <add/remove> <name> [link]", desc = "Manage emotes!")
public class Emote implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{
				"emote"
		};
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.TRUSTED;
	}

	@Override
	public void run(User sender, Guild origin, TextChannel channel, ArgumentList args) {
		if (!args.checkValid(2)) {
			ChatUtil.sendResponse(channel, sender, "Usage: emote <add/remove> <name> [link]");
			return;
		}

		if (args.get(0).equalsIgnoreCase("add")) {
			if (!args.checkValid(3)) {
				ChatUtil.sendResponse(channel, sender, "Usage: emote add <name> <link>");
				return;
			}

			try {
				EmoteManager.addEmote(args.get(1), new URL(args.get(2)));
				ChatUtil.sendResponse(channel, sender, "Emote added!");
			} catch (MalformedURLException e) {
				ChatUtil.sendResponse(channel, sender, "That URL was malformed! :(");
			}
		} else if (args.get(0).equalsIgnoreCase("remove")) {
			EmoteManager.removeEmote(args.get(1));

			ChatUtil.sendResponse(channel, sender, "Removed that emote.");
		}
	}
}
