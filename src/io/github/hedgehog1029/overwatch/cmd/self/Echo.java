package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.cmd.err.NoPermissionException;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.Finder;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import me.itsghost.jdiscord.Server;
import me.itsghost.jdiscord.talkable.Group;
import me.itsghost.jdiscord.talkable.User;

@Description(usage = "say <message> | !say <server> <channel> <message>", desc = "Tell me things to say!")
public class Echo implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{ "echo", "say" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.TRUSTED;
	}

	@Override
	public void run(User sender, Server origin, Group group, ArgumentList args) {
		Server target = Finder.findServer(args.get(0));

		if (target != null) {
			if (args.get(2) == null) ChatUtil.sendResponse(group, sender, "You didn't specify a message!");

			Group targetGroup = Finder.findGroup(target, args.get(1));

			if (targetGroup == null) {
				ChatUtil.sendResponse(group, sender, "Couldn't find that channel! Maybe I don't have access?");
				return;
			}

			targetGroup.sendMessage(args.getFrom(2));
		} else {
			group.sendMessage(args.getFull());
		}
	}
}
