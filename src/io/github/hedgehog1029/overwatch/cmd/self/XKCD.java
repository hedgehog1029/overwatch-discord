package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.net.NetConnection;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

@Description(desc = "Grab an XKCD!", usage = "xkcd [comic]")
public class XKCD implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{ "xkcd" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.NONE;
	}

	@Override
	public void run(User sender, Guild origin, TextChannel group, ArgumentList args) {
		try {
			String response;

			if (args.get(0) == null) {
				response = NetConnection.getData("http://xkcd.com/info.0.json");
			} else {
				response = NetConnection.getData(String.format("http://xkcd.com/%s/info.0.json", args.get(0)));
			}

			JSONObject data = (JSONObject) new JSONParser().parse(response);

			ChatUtil.sendResponse(group, sender, String.format("**%s**: %s\nAlt: %s", data.get("title"), data.get("img"), data.get("alt")));
		} catch (IOException e) {
			ChatUtil.sendResponse(group, sender, "I couldn't get the latest XKCD :(");
		} catch (ParseException e) {
			ChatUtil.sendResponse(group, sender, "Problem parsing the returned JSON!");
		}
	}
}
