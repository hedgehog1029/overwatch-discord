package io.github.hedgehog1029.overwatch.cmd.self;

import bsh.EvalError;
import bsh.Interpreter;
import io.github.hedgehog1029.overwatch.Overwatch;
import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

@Description(usage = "bsh <beanscript>", desc = "Execute a BeanShell input.")
public class BeanShell implements Command {

	private static Interpreter interpreter = new Interpreter();

	@Override
	public String[] getAliases() {
		return new String[]{ "beanshell", "bsh" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.ADMINISTRATOR;
	}

	@Override
	public void run(User sender, Guild origin, TextChannel group, ArgumentList args) {
		try {
			interpreter.set("api", Overwatch.getApi());
			interpreter.set("server", origin);
			interpreter.set("group", group);
			interpreter.set("sender", sender);
			interpreter.set("self", Overwatch.getApi().getSelfInfo());

			Object response = interpreter.eval(args.getOriginal());

			if (response != null)
				ChatUtil.sendResponse(group, sender, "Successfully executed script with the following output:\n>>> " + response);
			else
				ChatUtil.sendResponse(group, sender, "Successfully executed script with no output.");
		} catch (EvalError evalError) {
			ChatUtil.sendResponse(group, sender, "There was an internal BeanShell error!");
			evalError.printStackTrace();
		}
	}
}
