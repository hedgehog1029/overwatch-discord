package io.github.hedgehog1029.overwatch.cmd;

import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public interface Command {
	String[] getAliases();
	Rank getRequiredRank();
	void run(User sender, Guild origin, TextChannel channel, ArgumentList args);
}
