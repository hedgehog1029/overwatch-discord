package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.Finder;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.*;

@Description(usage = "pm <user> <message>", desc = "Tell me to send a user a PM!")
public class DirectMessage implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{ "dm", "pm" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.TRUSTED;
	}

	@Override
	public void run(User sender, Guild origin, TextChannel group, ArgumentList args) {
		User target = Finder.findUser(group, args.get(0));

		if (target == null) {
			ChatUtil.sendResponse(group, sender, String.format("I couldn't find user %s :(", args.get(0)));
			return;
		}

		target.getPrivateChannel().sendMessage(args.getFrom(1));
	}
}
