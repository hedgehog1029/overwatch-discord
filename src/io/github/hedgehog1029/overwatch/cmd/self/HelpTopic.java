package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.CommandManager;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.perms.PermissionManager;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import me.itsghost.jdiscord.Server;
import me.itsghost.jdiscord.talkable.Group;
import me.itsghost.jdiscord.talkable.User;

@Description(usage = "help", desc = "List commands and help!")
public class HelpTopic implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{ "help", "commands" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.NONE;
	}

	@Override
	public void run(User sender, Server origin, Group group, ArgumentList args) {
		Rank userRank = PermissionManager.getRank(sender) != null ? PermissionManager.getRank(sender) : Rank.NONE;

		ChatUtil.sendResponse(group, sender, CommandManager.getHelpManager().getHelpFor(userRank));
	}
}
