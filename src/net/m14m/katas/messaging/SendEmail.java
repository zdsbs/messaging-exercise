package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class SendEmail {

	private final Writer network;

	public SendEmail(Writer network) {
		this.network = network;
	}

	public void apply(String address, String body) {
		try {
			network.write("connect smtp\n" + "To: " + address + "\n" + "\n" + body + "\n" + "\n" + "disconnect\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
