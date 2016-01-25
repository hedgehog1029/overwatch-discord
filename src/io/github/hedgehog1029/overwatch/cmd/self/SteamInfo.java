package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.net.NetConnection;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import me.itsghost.jdiscord.Server;
import me.itsghost.jdiscord.talkable.Group;
import me.itsghost.jdiscord.talkable.User;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Description(usage = "steam <userid>", desc = "Get a Steam user's info!")
public class SteamInfo implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{ "steam" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.NONE;
	}

	private static String BASE_URL_ID = "http://steamcommunity.com/id/";
	private static String BASE_URL_PROFILE = "http://steamcommunity.com/profiles/";

	@Override
	public void run(User sender, Server origin, Group group, ArgumentList args) {
		if (args.get(0) == null) {
			ChatUtil.sendResponse(group, sender, "I need a Steam ID for that!");
			return;
		}

		String steamid = args.get(0);

		ChatUtil.sendResponse(group, sender, "Give me a minute to fetch things~");

		try {
			String response;

			if (steamid.matches("\\d+")) {
				response = NetConnection.getData(BASE_URL_PROFILE + steamid + "?xml=1");
			} else if (steamid.matches("\\w+")) {
				response = NetConnection.getData(BASE_URL_ID + steamid + "?xml=1");
			} else {
				ChatUtil.sendResponse(group, sender, "Uh... what was that?");
				return;
			}

			Pattern pattern = Pattern.compile("<steamID><!\\[CDATA\\[(.*)\\]\\]></steamID>.*<onlineState>(.*)</onlineState>.*<customURL><!\\[CDATA\\[(.*)\\]\\]></customURL>.*<memberSince>(.*)</memberSince>");
			Matcher m = pattern.matcher(response);

			if (m.find()) {
				ChatUtil.sendResponse(group, sender, "Steam user " + m.group(1) + " info:\n" +
						"Custom URL: http://steamcommunity.com/id/" + m.group(3) + "\n" +
						"State: " + m.group(2) + "\n" +
						"Member Since: " + m.group(4));
			} else {
				ChatUtil.sendResponse(group, sender, "Couldn't find that user!");
			}
		} catch (IOException e) {
			ChatUtil.sendResponse(group, sender, "There was a problem connecting to the Steam servers!");
		}
	}
}
