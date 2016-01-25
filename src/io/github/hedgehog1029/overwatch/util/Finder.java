package io.github.hedgehog1029.overwatch.util;

import io.github.hedgehog1029.overwatch.Overwatch;
import me.itsghost.jdiscord.Role;
import me.itsghost.jdiscord.Server;
import me.itsghost.jdiscord.talkable.Group;
import me.itsghost.jdiscord.talkable.User;

import java.util.ArrayList;
import java.util.List;

public class Finder {
	public static Server findServer(String name) {
		for (Server s : Overwatch.getApi().getAvailableServers()) {
			if (s.getName().equalsIgnoreCase(name)) return s;
		}

		return null;
	}

	public static Group findGroup(Server server, String name) {
		for (Group g : server.getGroups()) {
			if (g.getName().equalsIgnoreCase(name)) return g;
		}

		return null;
	}

	public static List<User> getUsersWithRole(Server server, Role role) {
		ArrayList<User> users = new ArrayList<>();

		server.getConnectedClients().forEach(user -> {
			if (user.getRoles().contains(role)) users.add(user.getUser());
		});

		return users;
	}

	public static Role findRole(Server server, String roleName) {

		return null;
	}
}
