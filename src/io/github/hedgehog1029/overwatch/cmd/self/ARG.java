package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.cmd.ServerWhitelist;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

@ServerWhitelist("129022124844253184")
@Description(desc = "Info about the current ARG.", usage = "arg")
public class ARG implements Command {

	@Override
	public String[] getAliases() {
		return new String[]{ "arg" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.NONE;
	}

	@Override
	public void run(User sender, Guild origin, TextChannel group, ArgumentList args) {
		ChatUtil.sendResponse(group, sender, "ARG Info:\n" +
				"Initial thread: https://www.reddit.com/r/gamedetectives/comments/42gihw/possible_arg_discovered_involving_multiple_steam/\n" +
				"Megathread: https://www.reddit.com/r/gamedetectives/comments/42ia72/multiple_steam_game_arg_mega_thread/");
	}
}
