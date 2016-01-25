package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.perms.PermissionManager;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import me.itsghost.jdiscord.Server;
import me.itsghost.jdiscord.talkable.Group;
import me.itsghost.jdiscord.talkable.GroupUser;
import me.itsghost.jdiscord.talkable.User;

@Description(usage = "userid", desc = "Get your userid!")
public class UserId implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{ "userid" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.NONE;
	}

	@Override
	public void run(User sender, Server origin, Group group, ArgumentList args) {
		if (args.get(0) == null) {
			ChatUtil.sendResponse(group, sender, "Your ID is " + sender.getId() + ".");
			return;
		}

		// Users can get their own ID, but not others.
		if (!PermissionManager.hasPermission(sender, Rank.TRUSTED)) {
			ChatUtil.sendResponse(group, sender, "I'm sorry, Dave. I can't let you do that.");
			return;
		}

		GroupUser target = origin.getGroupUserByUsername(args.get(0));

		if (target == null) {
			ChatUtil.sendResponse(group, sender, "Couldn't find user " + args.get(0) + "! :(");
			return;
		}

		ChatUtil.sendResponse(group, sender, "ID of user " + target.getUser().getUsername() + " is " + target.getUser().getId() + ".");
	}
}
