package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.cmd.ServerWhitelist;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import me.itsghost.jdiscord.Server;
import me.itsghost.jdiscord.talkable.Group;
import me.itsghost.jdiscord.talkable.User;

@ServerWhitelist("98424317108293632")
@Description(usage = "serverstatus", desc = "Get the status of the servers!")
public class ServerStatus implements Command {

	@Override
	public String[] getAliases() {
		return new String[]{ "serverstatus" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.NONE;
	}

	@Override
	public void run(User sender, Server origin, Group group, ArgumentList args) {

	}
}
