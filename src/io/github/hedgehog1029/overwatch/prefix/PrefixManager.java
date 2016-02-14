package io.github.hedgehog1029.overwatch.prefix;

import me.itsghost.jdiscord.Server;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class PrefixManager {
	private static HashMap<String, Character> prefixes = new HashMap<>();
	private static char defaultPrefix = '!';

	public static char getPrefix(String sid) {
		return prefixes.containsKey(sid) ? prefixes.get(sid) : defaultPrefix;
	}

	public static void setPrefix(String sid, char prefix) {
		if (prefixes.containsKey(sid))
			prefixes.replace(sid, prefix);
		else
			prefixes.put(sid, prefix);
	}

	public static String getCommand(Server server, String message) {
		return message.split(String.valueOf(getPrefix(server.getId())))[1].split("\\s")[0];
	}

	public static String getArgs(Server server, String message) {
		return message.replaceAll(getPrefix(server.getId()) + "\\w+\\s*", "");
	}

	public static void restore(JSONObject object) {
		object.forEach((k, v) -> prefixes.put((String) k, ((String) v).charAt(0)));
	}

	public static JSONObject toJSON() {
		JSONObject object = new JSONObject();

		prefixes.forEach((s, p) -> object.put(s, String.valueOf(p.charValue())));

		return object;
	}
}
