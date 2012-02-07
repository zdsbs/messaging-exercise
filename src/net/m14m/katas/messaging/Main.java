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
		String to = args[0];
		String body = args[1];
		NoAtSignInAddressChecker noAtSignInAddressChecker = new NoAtSignInAddressChecker(console);
		SendsEmail sendsEmail = new SendsEmail(network);
		Controller controller = new Controller();

		controller.check(noAtSignInAddressChecker).onNoError(sendsEmail);

		controller.doYourThing(to, body);
	}

}
