package io.github.hedgehog1029.overwatch;

import io.github.hedgehog1029.overwatch.cmd.CommandManager;
import io.github.hedgehog1029.overwatch.cmd.self.*;
import io.github.hedgehog1029.overwatch.event.OverwatchListener;
import io.github.hedgehog1029.overwatch.perms.PermissionManager;
import io.github.hedgehog1029.overwatch.prefix.PrefixManager;
import io.github.hedgehog1029.overwatch.sleep.SleepManager;
import io.github.hedgehog1029.overwatch.util.Pickle;
import me.itsghost.jdiscord.DiscordAPI;
import me.itsghost.jdiscord.DiscordBuilder;
import me.itsghost.jdiscord.exception.BadUsernamePasswordException;
import me.itsghost.jdiscord.exception.DiscordFailedToConnectException;
import me.itsghost.jdiscord.exception.NoLoginDetailsException;
import org.json.simple.JSONObject;

public class Overwatch {

	static DiscordAPI api;

	public static void main(String[] args) {
		System.out.println("[overwatch] Starting Overwatch (Discord Edition)");

		Pickle login = Pickle.open("login.json");

		JSONObject details = login.reader().read();

		api = new DiscordBuilder((String) details.get("email"), (String) details.get("password")).build();

		login.reader().end();

		try {
			api.login();
		} catch (DiscordFailedToConnectException | BadUsernamePasswordException | NoLoginDetailsException e) {
			e.printStackTrace();
		}

		api.getEventManager().registerListener(new OverwatchListener());

		CommandManager.registerCommand(new ARG());
		CommandManager.registerCommand(new BeanShell());
		CommandManager.registerCommand(new BotManage());
		CommandManager.registerCommand(new DirectMessage());
		CommandManager.registerCommand(new Echo());
		CommandManager.registerCommand(new Google());
		CommandManager.registerCommand(new HelpTopic());
		CommandManager.registerCommand(new Jokes());
		CommandManager.registerCommand(new SteamInfo());
		CommandManager.registerCommand(new Trust());
		CommandManager.registerCommand(new UserId());
		CommandManager.registerCommand(new XKCD());

		Pickle pickle = Pickle.open("data.json");

		JSONObject data = pickle.reader().read();

		PermissionManager.restore((JSONObject) data.get("people"));
		SleepManager.restore((JSONObject) data.get("muted"));
		PrefixManager.restore((JSONObject) data.get("prefixes"));

		pickle.reader().end();
	}

	public static void apiReady() {
		System.out.println("[overwatch] Overwatch started! Username: " + api.getSelfInfo().getUsername());
	}

	public static DiscordAPI getApi() {
		return api;
	}
}
