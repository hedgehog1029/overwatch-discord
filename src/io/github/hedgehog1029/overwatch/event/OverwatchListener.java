package io.github.hedgehog1029.overwatch.event;

import io.github.hedgehog1029.overwatch.Overwatch;
import io.github.hedgehog1029.overwatch.cmd.CommandManager;
import io.github.hedgehog1029.overwatch.util.ArgumentParser;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import me.itsghost.jdiscord.event.EventListener;
import me.itsghost.jdiscord.events.APILoadedEvent;
import me.itsghost.jdiscord.events.UserChatEvent;

public class OverwatchListener implements EventListener {

	public void userChat(UserChatEvent e) {
		String message = e.getMsg().getMessage();

		if (message.length() < 2) return;
		if (e.getUser().getUser().getId().equals(Overwatch.getApi().getSelfInfo().getId())) return;

		if (message.charAt(0) == '!') {
			String cmd = message.split("!")[1].split("\\s")[0];
			String sargs = message.replaceAll("!\\w+\\s*", "");

			ArgumentList args = ArgumentParser.parse(sargs);

			CommandManager.dispatch(cmd, e.getUser().getUser(), e.getGroup(), e.getServer(), args);
		}
	}

	public void load(APILoadedEvent e) {
		Overwatch.apiReady();
	}
}
