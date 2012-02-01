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

		badEmailAddress.otherwise(sendEmail);

		Runner runner = new Runner();
		runner.start(badEmailAddress).using(args);
	}
}
