package io.github.hedgehog1029.overwatch.cmd.err;

import io.github.hedgehog1029.overwatch.cmd.Command;
import net.dv8tion.jda.entities.User;

public class InsufficientArgumentsException extends BaseException {
	public InsufficientArgumentsException(User user, Command cmd) {
		super(user, cmd);
	}
}
