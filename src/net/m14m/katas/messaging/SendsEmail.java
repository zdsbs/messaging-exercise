package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class SendsEmail {

	private final Writer network;

	public SendsEmail(Writer network) {
		this.network = network;
	}

	public void sendMail(String to, String body) {
		try {
			network.write("connect smtp\n" + "To: " + to + "\n" + "\n" + body + "\n" + "\n" + "disconnect\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
