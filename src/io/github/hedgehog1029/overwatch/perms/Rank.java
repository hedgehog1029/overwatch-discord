package io.github.hedgehog1029.overwatch.perms;

import org.json.simple.JSONAware;

public enum Rank implements JSONAware {
	ADMINISTRATOR(10),
	TRUSTED(5),
	NONE(0);

	private int priority;

	Rank(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public String toJSONString() {
		return "\"" + this.toString() + "\"";
	}
}
