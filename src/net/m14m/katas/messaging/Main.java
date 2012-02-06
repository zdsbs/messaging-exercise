package net.m14m.katas.messaging;

import java.io.Writer;

public class Main {
	private static Writer network;
	private static Writer console;

	public static void setNetwork(Writer network) {
		Main.network = network;
	}

	public static void setConsole(Writer console) {
		Main.console = console;
	}

	public static void main(String... args) {
		SendEmail sendEmail = new SendEmail(network);
		BadEmailAddress badEmailAddress = new BadEmailAddress(console);
		MissingEmailBody missingEmailBody = new MissingEmailBody(console);
		ForeachEmailAddress foreachEmailAddress = new ForeachEmailAddress();
		missingEmailBody.otherwise(badEmailAddress).otherwise(noop());

		foreachEmailAddress.run(missingEmailBody).noErros(sendEmail);

		Runner runner = new Runner();
		runner.start(foreachEmailAddress).using(args);
	}

	private static Action noop() {
		return new Action() {

			@Override
			public void apply(String address, String body) {

			}
		};
	}
}
