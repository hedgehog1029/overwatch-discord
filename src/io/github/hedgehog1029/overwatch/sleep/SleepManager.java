package io.github.hedgehog1029.overwatch.sleep;

import net.dv8tion.jda.entities.Channel;
import net.dv8tion.jda.entities.Guild;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SleepManager {
	private static boolean GLOBAL_ENABLED = true;

	private static ArrayList<String> mutedServers = new ArrayList<>();
	private static ArrayList<String> mutedChannels = new ArrayList<>();
	private static ArrayList<String> overrideChannels = new ArrayList<>();

	public static void restore(JSONObject object) {
		((JSONArray) object.get("servers")).forEach(id -> mutedServers.add((String) id));
		((JSONArray) object.get("channels")).forEach(id -> mutedChannels.add((String) id));
		((JSONArray) object.get("override")).forEach(id -> overrideChannels.add((String) id));
	}

	/**
	 * Toggle the global sleep status.
	 * @return True if I'm now awake, false if not.
	 */
	public static boolean toggleGlobal() {
		GLOBAL_ENABLED = !GLOBAL_ENABLED;

		return GLOBAL_ENABLED;
	}

	public static boolean toggleServer(Guild server) {
		boolean muted = mutedServers.contains(server.getId());

		if (muted) mutedServers.remove(server.getId());
		else mutedServers.add(server.getId());

		return !muted;
	}

	public static boolean toggleChannel(Channel group) {
		boolean muted = mutedChannels.contains(group.getId());

		if (muted) mutedChannels.remove(group.getId());
		else mutedChannels.add(group.getId());

		return !muted;
	}

	public static boolean toggleOverride(Channel group) {
		boolean overriden = overrideChannels.contains(group.getId());

		if (overriden) overrideChannels.remove(group.getId());
		else overrideChannels.add(group.getId());

		return !overriden;
	}

	public static boolean willRespond(String command, Channel group, Guild server) {
		if (command.equalsIgnoreCase("manage")) return true;

		if (overrideChannels.contains(group.getId())) return true;
		if (mutedChannels.contains(group.getId())) return false;
		if (mutedServers.contains(server.getId())) return false;

		return GLOBAL_ENABLED;
	}

	public static ArrayList<String> getMutedChannels() {
		return mutedChannels;
	}

	public static ArrayList<String> getMutedServers() {
		return mutedServers;
	}

	public static ArrayList<String> getOverrideChannels() {
		return overrideChannels;
	}
}
