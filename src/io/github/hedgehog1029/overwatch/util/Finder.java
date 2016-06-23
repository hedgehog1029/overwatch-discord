package io.github.hedgehog1029.overwatch.util;

import io.github.hedgehog1029.overwatch.Overwatch;
import net.dv8tion.jda.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Finder {
	public static Guild findGuild(String name) {
		return Overwatch.getApi().getGuilds().parallelStream().filter(guild -> guild.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public static TextChannel findGroup(Guild guild, String name) {
		return guild.getTextChannels().parallelStream().filter(channel -> channel.getName().equals(name)).findFirst().orElse(null);
	}

	public static VoiceChannel findVc(Guild guild, String name) {
		return guild.getVoiceChannels().parallelStream().filter(vc -> vc.getName().equals(name)).findFirst().orElse(null);
	}

	public static User findUser(Channel channel, String name) {
		return channel.getUsers().parallelStream().filter(u -> u.getUsername().equals(name)).findFirst().orElse(null);
	}

	public static VoiceChannel findVoiceChannelOfUser(User user, Guild guild) {
		return guild.getVoiceChannels().parallelStream().filter(vc -> vc.getUsers().contains(user)).findFirst().orElse(null);
	}

	public static List<User> getUsersWithRole(Guild guild, Role role) {
		ArrayList<User> users = new ArrayList<>();

		guild.getUsers().forEach(user -> {
			if (guild.getRolesForUser(user).contains(role)) users.add(user);
		});

		return users;
	}

	public static Role findRole(Guild guild, String roleName) {
		return guild.getRoles().parallelStream().filter(role -> role.getName().equalsIgnoreCase(roleName)).findFirst().get();
	}
}
