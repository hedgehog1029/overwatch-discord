package io.github.hedgehog1029.overwatch.mod;

import io.github.hedgehog1029.overwatch.util.ImageUtil;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class EmoteManager {
	private static HashMap<String, Emote> emotes = new HashMap<>();

	public static boolean addEmote(String name, URL source) {
		if (emotes.containsKey(name)) return false;

		Emote emote = new Emote(String.format("img/%s.png", name), name);
		emotes.put(name, emote);

		ImageUtil.downloadImage(emote, source);

		return true;
	}

	public static void removeEmote(String name) {
		emotes.remove(name);
	}

	public static Emote getEmote(String name) {
		return emotes.get(name);
	}

	public static JSONObject serialize() {
		return new JSONObject(emotes);
	}

	public static void restore(JSONObject object) {
		object.forEach((k, v) -> emotes.put((String) k, Emote.deserialize((JSONObject) v)));
	}

	public static class Emote implements JSONAware {
		private String filepath;
		private String name;

		public Emote(String filepath, String name) {
			this.filepath = filepath;
			this.name = name;
		}

		public File getFile() {
			return new File(this.filepath);
		}

		public String getFilepath() {
			return filepath;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toJSONString() {
			return String.format("{ \"name\": \"%s\", \"filepath\": \"%s\" }", this.name, this.filepath);
		}

		public static Emote deserialize(JSONObject object) {
			return new Emote((String) object.get("filepath"), (String) object.get("name"));
		}
	}
}
