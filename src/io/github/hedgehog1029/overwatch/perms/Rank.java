package io.github.hedgehog1029.overwatch.perms;

public enum  Rank {
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
