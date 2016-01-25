package io.github.hedgehog1029.overwatch.util;

import me.itsghost.jdiscord.talkable.Group;
import me.itsghost.jdiscord.talkable.User;

public class ChatUtil {
	public static void sendResponse(Group dest, User mention, String message) {
		dest.sendMessage("<@" + mention.getId() + ">: " + message);
	}
}
