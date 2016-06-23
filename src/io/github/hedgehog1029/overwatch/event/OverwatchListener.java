package io.github.hedgehog1029.overwatch.event;

import io.github.hedgehog1029.overwatch.Overwatch;
import io.github.hedgehog1029.overwatch.cmd.CommandManager;
import io.github.hedgehog1029.overwatch.mod.EmoteManager;
import io.github.hedgehog1029.overwatch.prefix.PrefixManager;
import io.github.hedgehog1029.overwatch.util.ArgumentParser;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OverwatchListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		String message = e.getMessage().getContent();

		if (message.length() < 2) return;
		if (e.getAuthor().getId().equals(Overwatch.getApi().getSelfInfo().getId())) return;

		Pattern pattern = Pattern.compile(":(\\w+):");
		Matcher matcher = pattern.matcher(message);

		if (matcher.matches()) {
			String emote = matcher.group(1);
			EmoteManager.Emote emote1 = EmoteManager.getEmote(emote);

			if (emote1 != null) {
				e.getChannel().sendFileAsync(emote1.getFile(), null, null);
			}

			return;
		}

		String cmd = "";
		String sargs;
		ArgumentList args = null;

		if (e.isPrivate() && message.charAt(0) == PrefixManager.getDefaultPrefix()) {
			cmd = PrefixManager.extractCommand(PrefixManager.getDefaultPrefix(), message);
			sargs = PrefixManager.extractArgs(PrefixManager.getDefaultPrefix(), message);

			args = ArgumentParser.parse(sargs);
		} else if (message.charAt(0) == PrefixManager.getPrefix(e.getGuild().getId())) {
			cmd = PrefixManager.getCommand(e.getGuild(), message);
			sargs = PrefixManager.getArgs(e.getGuild(), message);

			args = ArgumentParser.parse(sargs);
		}

		CommandManager.dispatch(cmd, e.getAuthor(), e.getTextChannel(), e.getGuild(), args);
	}

	public void onReady(ReadyEvent e) {
		Overwatch.apiReady();
	}
}
