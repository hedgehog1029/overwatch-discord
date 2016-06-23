package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.TimeParser;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Description(usage = "remindme <time> <note>", desc = "Get a reminder <time> from now!")
public class Reminder implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{ "remindme" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.NONE;
	}

	@Override
	public void run(User sender, Guild origin, TextChannel channel, ArgumentList args) {
		if (!args.checkValid(2)) {
			ChatUtil.sendResponse(channel, sender, "Not enough arguments!");
			return;
		}

		String time = args.get(0);
		String note = args.getFrom(1);

		Calendar future = TimeParser.future(time);

	}
}
