package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.Overwatch;
import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.mod.EmoteManager;
import io.github.hedgehog1029.overwatch.perms.PermissionManager;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.prefix.PrefixManager;
import io.github.hedgehog1029.overwatch.sleep.SleepManager;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.Pickle;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.*;
import net.dv8tion.jda.utils.InviteUtil;
import org.json.simple.JSONObject;

@Description(usage = "manage <action>", desc = "Manage my settings!")
public class BotManage implements Command {

	@Override
	public String[] getAliases() {
		return new String[]{ "manage" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.ADMINISTRATOR;
	}

	@Override
	public void run(User sender, Guild origin, TextChannel group, ArgumentList args) {
		switch(args.get(0)) {
			case "shutdown":
				group.sendMessage("Shutting down now! I'll be back soon, probably!");
				Overwatch.getApi().shutdown();
				break;
			case "sleep":
				if (args.get(1) != null) {
					String f = args.get(1);

					if (f.equalsIgnoreCase("server")) {
						boolean state = SleepManager.toggleServer(origin);

						if (state) ChatUtil.sendResponse(group, sender, "Server " + origin.getName() + " is now muted!");
						else ChatUtil.sendResponse(group, sender, "Server " + origin.getName() + " is no longer muted.");
					} else if (f.equalsIgnoreCase("channel")) {
						boolean state = SleepManager.toggleChannel(group);

						if (state) ChatUtil.sendResponse(group, sender, "Channel " + group.getName() + " is now muted.");
						else ChatUtil.sendResponse(group, sender, "Channel " + group.getName() + " is no longer muted.");
					} else if (f.equalsIgnoreCase("override")) {
						boolean state = SleepManager.toggleOverride(group);

						if (state) ChatUtil.sendResponse(group, sender, "Channel " + group.getName() + " is now overridden, I'll be listening!");
						else ChatUtil.sendResponse(group, sender, "Channel " + group.getName() + " is no longer overridden.");
					} else ChatUtil.sendResponse(group, sender, "You didn't specify a mode as argument 2!");

					return;
				}

				boolean state = SleepManager.toggleGlobal();

				if (state)
					ChatUtil.sendResponse(group, sender, "Hi! I'm awake!");
				else
					ChatUtil.sendResponse(group, sender, "Oh, it's nighttime. *thud*");

				break;
			case "join":
				String joinId = args.get(1);

				if (joinId == null) {
					ChatUtil.sendResponse(group, sender, "I need an invite to join a server!");
					return;
				}

				joinId = joinId.replaceAll("(?:http(?:s)*://)*discord\\.gg[/]*", "");

				InviteUtil.join(joinId, Overwatch.getApi(), (g) -> {
					ChatUtil.sendResponse(group, sender, String.format("Joined %s!", g.getName()));
				});

				break;
			case "save":
				Pickle pickle = Pickle.open("data.json");

				JSONObject parent = new JSONObject();
				JSONObject people = new JSONObject(PermissionManager.getPeople());
				JSONObject muted = new JSONObject();

				muted.put("servers", SleepManager.getMutedServers());
				muted.put("channels", SleepManager.getMutedChannels());
				muted.put("override", SleepManager.getOverrideChannels());

				parent.put("people", people);
				parent.put("muted", muted);
				parent.put("prefixes", PrefixManager.toJSON());
				parent.put("emotes", EmoteManager.serialize());

				pickle.writer().write(parent);
				pickle.writer().end();

				ChatUtil.sendResponse(group, sender, "Successfully saved my data! ^w^");
				break;
			case "prefix":
				char prefix = args.get(1).charAt(0);

				PrefixManager.setPrefix(origin.getId(), prefix);

				ChatUtil.sendResponse(group, sender, "Prefix for " + origin.getName() + " set to `" + prefix + "`!");
				break;
			case "dance":
				ChatUtil.sendResponse(group, sender, "Now dancing... ╚(ಠ_ಠ)=┐");
				break;
			case "kirbydance":
				group.sendMessage("<('-'<)<('-')>(>'-')>");
				break;
			default:
				ChatUtil.sendResponse(group, sender, "You need to specify an action! Available actions: \n" +
						"`shutdown, sleep, join, save, prefix, dance, kirbydance`");
		}
	}
}
