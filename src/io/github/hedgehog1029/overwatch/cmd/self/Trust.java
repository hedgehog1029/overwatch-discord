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

@Description(usage = "trust <user>", desc = "Trust a kind person! Or untrust them. I don't judge.")
public class Trust implements Command {

	@Override
	public String[] getAliases() {
		return new String[]{ "trust" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.TRUSTED;
	}

	@Override
	public void run(User sender, Server origin, Group group, ArgumentList args) {
		GroupUser target = origin.getGroupUserByUsername(args.get(0));

		if (target == null) {
			ChatUtil.sendResponse(group, sender, "User " + args.get(0) + " not found!");
			return;
		}

		if (PermissionManager.getRank(target.getUser()) == Rank.ADMINISTRATOR) {
			ChatUtil.sendResponse(group, sender, "You can't untrust an administrator!");
			return;
		}

		if (PermissionManager.getRank(target.getUser()) == Rank.TRUSTED) {
			PermissionManager.demote(target.getUser());
			ChatUtil.sendResponse(group, sender, String.format("%s is no longer trusted!", target.getUser().getUsername()));
		} else {
			PermissionManager.promote(target.getUser());
			ChatUtil.sendResponse(group, sender, String.format("%s is now trusted!", target.getUser().getUsername()));
		}
	}
}
