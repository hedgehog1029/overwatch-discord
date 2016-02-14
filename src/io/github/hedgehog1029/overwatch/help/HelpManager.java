package io.github.hedgehog1029.overwatch.help;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.CommandManager;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.cmd.ServerWhitelist;
import io.github.hedgehog1029.overwatch.perms.Rank;
import me.itsghost.jdiscord.Server;
import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HelpManager {

	private HashMap<Rank, List<Command>> sorted = new HashMap<>();

	public HelpManager() {
		for (Rank r : Rank.values()) {
			sorted.put(r, new ArrayList<>());
		}
	}

	public void append(Command c) {
		sorted.get(c.getRequiredRank()).add(c);
	}

	public String getHelpFor(Server server, Rank rank) {
		StringBuilder result = new StringBuilder();

		switch (rank) {
			case ADMINISTRATOR:
				StringBuilder admin = new StringBuilder();
				admin.append("Admin-only commands:\n");

				this.appendCommands(server, Rank.ADMINISTRATOR, admin);
				result.append(admin);
			case TRUSTED:
				StringBuilder trust = new StringBuilder();
				trust.append("Trusted commands:\n");

				this.appendCommands(server, Rank.TRUSTED, trust);
				result.append(trust);
			default:
				StringBuilder norm = new StringBuilder();
				norm.append("Public commands:\n");

				this.appendCommands(server, Rank.NONE, norm);
				result.append(norm);
		}

		return result.toString();
	}

	private void appendCommands(Server server, Rank rank, StringBuilder builder) {
		sorted.get(rank).forEach(c -> {
			Description description = c.getClass().getDeclaredAnnotation(Description.class);
			ServerWhitelist whitelist = c.getClass().getDeclaredAnnotation(ServerWhitelist.class);

			if (description == null) return;
			if (whitelist != null) {
				if (Arrays.asList(whitelist.value()).contains(server.getId())) builder.append(format(description));
			} else builder.append(format(description));
		});
	}

	private String format(Description desc) {
		return String.format("`!%s: %s`\n", desc.usage(), desc.desc());
	}
}
