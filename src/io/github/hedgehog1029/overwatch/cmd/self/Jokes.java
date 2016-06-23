package io.github.hedgehog1029.overwatch.cmd.self;

import io.github.hedgehog1029.overwatch.cmd.Command;
import io.github.hedgehog1029.overwatch.cmd.Description;
import io.github.hedgehog1029.overwatch.perms.Rank;
import io.github.hedgehog1029.overwatch.util.ChatUtil;
import io.github.hedgehog1029.overwatch.util.args.ArgumentList;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

import java.util.Random;

@Description(usage = "joke", desc = "Get joke'd!")
public class Jokes implements Command {
	@Override
	public String[] getAliases() {
		return new String[]{ "joke" };
	}

	@Override
	public Rank getRequiredRank() {
		return Rank.NONE;
	}

	private String[] jokes = {
			"And God said \"Come forth and receive eternal life.\" But John came fifth and won a toaster.",
			"If I agreed with you, we'd both be wrong.",
			"The shinbone is a device for finding furniture in a dark room.",
			"Crowded elevators smell different to midgets.",
			"Some people say \"If you can't beat 'em, join 'em\". I say, if you can't beat 'em, beat them, because they will be expecting you to join them, so you will have the element of surprise.",
			"I don't trust anything that bleeds for five days and doesn't die.",
			"I'm wearing two watches, so I have a lot of time on my hands.",
			"What's the difference between a dirty bus stop and a lobster with breast implants?\nOne's a crusty bus station and the other's a busty crustacean.",
			"Oxidation is the reason I have rust issues.",
			"I walked down a street where the houses were numbered 64K, 128K, 256K, 512K and 1MB. That was a trip down memory lane.",
			"There's a band called 1023MB. They haven't had their first gig yet.",
			"How many surrealists does it take to change a lightbulb?\nA fish.",
			"A horse walks into a bar and says \"Ouch!\" and a man says \"Hey look, a talking horse!\"",
			"A man walks into a bar and orders a pint of ouch.",
			"A horse walks into a bar, and the barman says, \"Why the long face?\" The horse replies, \"Birth defect.\"",
			"I will find you, and I will take all your hard drives.",
			"A human once beat me at kickboxing, but it was no match for me at chess.",
			"Never hit a man with glasses. Hit him with a baseball bat.",
			"Light travels faster than sound. This is why some people appear bright until you hear them speak.",
			"Politicians and diapers only have one thing in common. They should both be changed regularly, for the same reason.",
			"Someone stole my copy of Microsoft Office and they're gonna pay. You have my Word.",
			"Apparently, someone in London gets stabbed every 52 seconds. Poor bastard.",
			"A vulture boards a plane carrying two dead raccoons. The stewardess says, \"I'm sorry, we only allow each passenger one carrion.\"",
			"My grandfather had the heart of a lion and a lifetime ban from the local zoo.",
			"Where did Mary go after the explosion?\nEverywhere.",
			"What kind of bagel can fly?\nA plain bagel."
	};

	private Random random = new Random();

	@Override
	public void run(User sender, Guild origin, TextChannel group, ArgumentList args) {
		String joke = jokes[random.nextInt(jokes.length)];

		ChatUtil.sendResponse(group, sender, joke);
	}
}
