package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class NotifiesConsoleOfEmptyBodyError {
	
	private final Writer console;
	
	public NotifiesConsoleOfEmptyBodyError(Writer console) {
		this.console = console;
	}
	
	public void send() {
		try {
			console.write("Cannot send an email with no body.\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
