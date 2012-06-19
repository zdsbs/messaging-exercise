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
		Body bodyIsLastArg = new BodyIsLastArg(args);
		EmailAddresses emailAddressesComeBeforeLastArg = new EmailAddressesComeBeforeLastArg(args);
		
		new Controller(emailAddressesComeBeforeLastArg, bodyIsLastArg).//
		process(new SingleEmailAddressNeedsAtSigns(console)).//
		process(new MultipleEmailAddressNeedsAtSigns(console)).//
		process(new BodiesCannotBeEmpty(console)).//
		process(new SendSingleEmailWithNoErrors(network)).//
		process(new SendMultipleEmails(network)).//
		run();
		
	}
	
}
