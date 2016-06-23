package io.github.hedgehog1029.overwatch;

import io.github.hedgehog1029.overwatch.cmd.CommandManager;
import io.github.hedgehog1029.overwatch.cmd.self.*;
import io.github.hedgehog1029.overwatch.event.OverwatchListener;
import io.github.hedgehog1029.overwatch.mod.EmoteManager;
import io.github.hedgehog1029.overwatch.perms.PermissionManager;
import io.github.hedgehog1029.overwatch.prefix.PrefixManager;
import io.github.hedgehog1029.overwatch.sleep.SleepManager;
import io.github.hedgehog1029.overwatch.util.Pickle;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import org.json.simple.JSONObject;

import javax.security.auth.login.LoginException;

public class Overwatch {

	static JDA api;

	public static void main(String[] args) {
		System.out.println("[overwatch] Starting Overwatch (Discord Edition)");

		Pickle login = Pickle.open("login.json");

		JSONObject details = login.reader().read();

		JDABuilder jdab = new JDABuilder()
				.setBotToken((String) details.get("token"))
				.addListener(new OverwatchListener());

		login.reader().end();

		try {
			api = jdab.buildAsync();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		CommandManager.registerCommand(new ARG());
		CommandManager.registerCommand(new BeanShell());
		CommandManager.registerCommand(new BotManage());
		CommandManager.registerCommand(new DirectMessage());
		CommandManager.registerCommand(new Echo());
		CommandManager.registerCommand(new Emote());
		CommandManager.registerCommand(new Google());
		CommandManager.registerCommand(new HelpTopic());
		CommandManager.registerCommand(new Jokes());
		CommandManager.registerCommand(new Music());
		CommandManager.registerCommand(new Random());
		CommandManager.registerCommand(new SteamInfo());
		CommandManager.registerCommand(new Trust());
		CommandManager.registerCommand(new UserId());
		CommandManager.registerCommand(new XKCD());

		Pickle pickle = Pickle.open("data.json");

		JSONObject data = pickle.reader().read();

		PermissionManager.restore((JSONObject) data.get("people"));
		SleepManager.restore((JSONObject) data.get("muted"));
		PrefixManager.restore((JSONObject) data.get("prefixes"));
		EmoteManager.restore((JSONObject) data.get("emotes"));

		pickle.reader().end();
	}

	public static void apiReady() {
		System.out.println("[overwatch] Overwatch started! Username: " + api.getSelfInfo().getUsername());
	}

	public static JDA getApi() {
		return api;
	}
}
