package net.m14m.katas.messaging_endtoendtest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import net.m14m.katas.messaging.Main;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class EndToEndTest {
	private static final String NO_OUTPUT = "";
	private final StringWriter network = new StringWriter();
	private final StringWriter console = new StringWriter();
	
	@Before
	public void configureMainClassWithFakeOutputs() {
		Main.setNetwork(network);
		Main.setConsole(console);
	}
	
	@Test
	public void sendAnEmail() {
		Main.main("joe@example.com", "Hi there!");
		networkShouldReceive("connect smtp\n" + "To: joe@example.com\n" + "\n" + "Hi there!\n" + "\n" + "disconnect\n");
		consoleShouldReceive(NO_OUTPUT);
	}
	
	@Test
	public void sendAnEmail_AnotherExample() {
		Main.main("sally@example.com", "Greetings.\nHow's it going?");
		networkShouldReceive("connect smtp\n" + "To: sally@example.com\n" + "\n" + "Greetings.\n" + "How's it going?\n" + "\n" + "disconnect\n");
		consoleShouldReceive(NO_OUTPUT);
	}
	
	@Test
	public void showAnErrorAndDoNotSendIfTheEmailAddressIsInvalid() {
		Main.main("noatsign", "Hi there!");
		networkShouldReceive(NO_OUTPUT);
		consoleShouldReceive("Invalid email address: noatsign\n");
	}
	
	@Test
	public void showAnErrorAndDoNotSendIfTheBodyIsInvalid() {
		Main.main("dinah@example.com", "");
		networkShouldReceive(NO_OUTPUT);
		consoleShouldReceive("Cannot send an email with no body.\n");
	}
	
	@Test
	public void sendAnEmailToMultipleAddresses() {
		Main.main("sally@example.com", "joe@example.com", "Hi everyone!");
		networkShouldReceive("connect smtp\n" + "To: sally@example.com\n" + "To: joe@example.com\n" + "\n" + "Hi everyone!\n" + "\n" + "disconnect\n");
		consoleShouldReceive(NO_OUTPUT);
	}
	
	@Test
	public void showAnErrorAndDoNotSendIfOneOfManyEmailAddressesAreInvalid() throws Exception {
		Main.main("noatsign", "joe@example.com", "Hi everyone!");
		networkShouldReceive(NO_OUTPUT);
		consoleShouldReceive("Invalid email address: noatsign\n");
	}
	
	@Test
	public void showAnErrorForEachInvalidEmailAddress() throws Exception {
		Main.main("noatsign", "noatsign2", "Hi everyone!");
		networkShouldReceive(NO_OUTPUT);
		consoleShouldReceive("Invalid email addresses: noatsign,noatsign2\n");
	}
	
	@Ignore
	@Test
	public void sendAMessageInAnotherFormat() {
		Main.main("-im", "leslie@chat.example.com", ":-) hey there!");
		networkShouldReceive("connect chat\n" + "<leslie@chat.example.com>(:-\\) hey there!)\n" + "disconnect\n");
		consoleShouldReceive(NO_OUTPUT);
	}
	
	@Ignore
	@Test
	public void handleErrorsGracefully() {
		Main.setNetwork(new BadNetworkConnection());
		Main.main("joe@example.com", "Hi there!");
		consoleShouldReceive("Connection error. Please try again.\n");
	}
	
	@Ignore
	@Test
	public void chatsToMultipleAddressesGetSentIndividually() {
		Main.main("-im", "leslie@chat.example.com,joey@chat.example.com", "Hello.");
		networkShouldReceive("connect chat\n" + "<leslie@chat.example.com>(Hello.)\n" + "<joey@chat.example.com>(Hello.)\n" + "disconnect\n");
		consoleShouldReceive(NO_OUTPUT);
	}
	
	private void networkShouldReceive(String output) {
		assertEquals(output, network.toString());
	}
	
	private void consoleShouldReceive(String output) {
		assertEquals(output, console.toString());
	}
	
	private static class BadNetworkConnection extends Writer {
		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
			throw new IOException("Oh no the network is down!!!!!!!111one");
		}
		
		@Override
		public void flush() throws IOException {
		}
		
		@Override
		public void close() throws IOException {
		}
	}
}
