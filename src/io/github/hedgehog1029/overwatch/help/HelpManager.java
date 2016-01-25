package io.github.hedgehog1029.overwatch.help;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.perms.Rank;

public class HelpManager {
	private StringBuilder normal = new StringBuilder();
	private StringBuilder trusted = new StringBuilder();
	private StringBuilder admin = new StringBuilder();

	public HelpManager() {
		normal.append("Public commands: \n");
		trusted.append("Trusted commands: \n");
		admin.append("Admin-only commands: \n");
	}

	public void append(Command c) {
		Description descriptor = c.getClass().getDeclaredAnnotation(Description.class);

		if (descriptor == null) return; // if no descriptor, assume 'hidden' command

		switch (c.getRequiredRank()) {
			case NONE:
				normal.append("`!").append(descriptor.usage()).append(": ").append(descriptor.desc()).append("`\n");
				break;
			case TRUSTED:
				trusted.append("`!").append(descriptor.usage()).append(": ").append(descriptor.desc()).append("`\n");
				break;
			case ADMINISTRATOR:
				admin.append("`!").append(descriptor.usage()).append(": ").append(descriptor.desc()).append("`\n");
				break;
			default: break;
		}
	}

	public String getHelpFor(Rank rank) {
		StringBuilder help = new StringBuilder();

		switch (rank) {
			case ADMINISTRATOR:
				help.append(admin.toString());
			case TRUSTED:
				help.append(trusted.toString());
			case NONE:
				help.append(normal.toString());
				break;
			default:
				help.append(normal.toString());
		}

		return help.toString();
	}
}
