package io.github.hedgehog1029.overwatch.cmd;

import io.github.hedgehog1029.overwatch.help.HelpManager;
import io.github.hedgehog1029.overwatch.perms.PermissionManager;
import io.github.hedgehog1029.overwatch.sleep.SleepManager;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import me.itsghost.jdiscord.Server;
import me.itsghost.jdiscord.talkable.Group;
import me.itsghost.jdiscord.talkable.User;

import java.util.HashMap;

public class CommandManager {
	private static HashMap<String, Command> commands = new HashMap<>();
	private static HelpManager helpManager = new HelpManager();

	/**
	 * Register a command.
	 *
	 * @param cmd The command to be registered.
	 */
	public static void registerCommand(Command cmd) {
		for (String alias : cmd.getAliases())
			commands.put(alias, cmd);

		helpManager.append(cmd);
	}

	/**
	 * @deprecated HelpManager does not currently support removing help topics.
	 *
	 * Unregister a command by its alias.
	 *
	 * @param alias An alias the command is known by.
	 */
	@Deprecated
	public static void unregisterCommand(String alias) {
		for (String a : commands.get(alias).getAliases())
			commands.remove(a);
	}

	public static HelpManager getHelpManager() {
		return helpManager;
	}

	public static void dispatch(String command, User sender, Group group, Server server, ArgumentList args) {
		Command cmd = commands.get(command);

		if (cmd == null) return; // if the command isn't known, do nothing; no spam!

		if (!SleepManager.willRespond(command, group, server)) return;
		if (!PermissionManager.hasPermission(sender, cmd.getRequiredRank()))  {
			ChatUtil.sendResponse(group, sender, "I'm sorry, Dave. I can't let you do that.");
			return;
		}

		if (cmd.getClass().isAnnotationPresent(ServerWhitelist.class)) {
			ServerWhitelist whitelist = cmd.getClass().getDeclaredAnnotation(ServerWhitelist.class);

			for (String id : whitelist.value())
				if (server.getId().equalsIgnoreCase(id))
					cmd.run(sender, server, group, args);

			return;
		}

		cmd.run(sender, server, group, args);
	}
}
