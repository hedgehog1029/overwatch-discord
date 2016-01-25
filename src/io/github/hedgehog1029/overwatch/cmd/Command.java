package io.github.hedgehog1029.overwatch.cmd;

import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import me.itsghost.jdiscord.Server;
import me.itsghost.jdiscord.talkable.Group;
import me.itsghost.jdiscord.talkable.User;

public interface Command {
	String[] getAliases();
	Rank getRequiredRank();
	void run(User sender, Server origin, Group group, ArgumentList args);
}
