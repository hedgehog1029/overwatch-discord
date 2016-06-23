package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.cmd.ServerWhitelist;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

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
	public void run(User sender, Guild origin, TextChannel group, ArgumentList args) {

	}
}
