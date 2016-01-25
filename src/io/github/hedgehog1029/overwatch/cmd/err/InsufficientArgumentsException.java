package io.github.hedgehog1029.overwatch.cmd.err;

import io.github.hedgehog1029.overwatch.cmd.Command;
import me.itsghost.jdiscord.talkable.User;

public class InsufficientArgumentsException extends BaseException {
	public InsufficientArgumentsException(User user, Command cmd) {
		super(user, cmd);
	}
}
