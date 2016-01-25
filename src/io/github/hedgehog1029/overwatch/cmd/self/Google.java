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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

@Description(usage = "g <search>", desc = "Google things!")
public class Google implements Command {

	private static String BASE_URL = "https://www.googleapis.com/customsearch/v1?key=AIzaSyAruE7wV7LaL1tZ1XJRHCtA7pmuz9EfXl8&cx=006735756282586657842:s7i_4ej9amu&q=";

	@Override
	public String[] getAliases() {
		return new String[]{ "google", "g" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.NONE;
	}

	@Override
	public void run(User sender, Server origin, Group group, ArgumentList args) {
		try {
			String url = BASE_URL + args.getFull("+");
			String result = NetConnection.getData(url);

			JSONObject json = (JSONObject) new JSONParser().parse(result);

			if (json.get("items") != null) {
				JSONArray items = (JSONArray) json.get("items");
				JSONObject item = (JSONObject) items.get(0);

				ChatUtil.sendResponse(group, sender, item.get("snippet").toString().replaceAll("(?:http(?:s)*://)*discord\\.gg[/*]\\w*", "[REDACTED]") + "\nFrom " + item.get("link"));
			} else ChatUtil.sendResponse(group, sender, "I couldn't find anything useful! Sorry :(");
		} catch (IOException e) {
			ChatUtil.sendResponse(group, sender, "There was an error!\n" + e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
