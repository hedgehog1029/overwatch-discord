package io.github.hedgehog1029.overwatch.cmd.err;

import io.github.hedgehog1029.overwatch.cmd.Command;
import me.itsghost.jdiscord.talkable.User;

public class BaseException extends Exception {

	private User user;
	private Command command;

	public BaseException(User user, Command cmd) {
		this.user = user;
		this.command = cmd;
	}

	public Command getCommand() {
		return command;
	}

	public User getUser() {
		return user;
	}
}
