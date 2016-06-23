package io.github.hedgehog1029.overwatch.perms;

import net.dv8tion.jda.entities.User;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PermissionManager {
	private static HashMap<String, Rank> ranks = new HashMap<>();

	public static String[] ADMINISTRATORS = { "97707213690249216" };

	static {
		Arrays.asList(ADMINISTRATORS).forEach(s -> ranks.put(s, Rank.ADMINISTRATOR));
	}

	public static void restore(JSONObject object) {
		object.forEach((k, v) -> ranks.put((String) k, Rank.valueOf((String) v)));
	}

	public static void promote(User user) {
		String id = user.getId();

		if (!ranks.containsKey(id))
			ranks.put(id, Rank.TRUSTED);
		else
			ranks.replace(id, Rank.TRUSTED);
	}

	public static void demote(User user) {
		String id = user.getId();

		if (!ranks.containsKey(id))
			ranks.put(id, Rank.NONE);
		else
			ranks.replace(id, Rank.NONE);
	}

	public static Rank getRank(User user) {
		return ranks.get(user.getId());
	}

	public static boolean hasPermission(User user, Rank requiredRank) {
		Rank rank = ranks.get(user.getId());

		if (rank == null) rank = Rank.NONE;

		return rank.getPriority() >= requiredRank.getPriority();
	}

	/**
	 * Get a formatted Map of userids mapped to rank.
	 * @return Map of UserID:Rank
	 */
	public static Map<String, String> getPeople() {
		HashMap<String, String> map = new HashMap<>();

		ranks.forEach((uid, rank) -> {
			if (rank == Rank.ADMINISTRATOR) return;

			map.put(uid, rank.toJSONString());
		});

		return map;
	}
}
