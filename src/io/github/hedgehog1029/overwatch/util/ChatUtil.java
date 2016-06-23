package io.github.hedgehog1029.overwatch.util;

import net.dv8tion.jda.entities.MessageChannel;
import net.dv8tion.jda.entities.User;

public class ChatUtil {
	public static void sendResponse(MessageChannel dest, User mention, String message) {
		dest.sendMessage("<@" + mention.getId() + ">: " + message);
	}
}
