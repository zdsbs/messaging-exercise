package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class BadEmailAddress implements Action {

	private final Writer console;
	private Action otherwise;

	public BadEmailAddress(Writer console) {
		this.console = console;
	}

	public void otherwise(Action action) {
		this.otherwise = action;
	}

	@Override
	public void apply(String address, String body) {
		if (address.contains("@") == false) {
			try {
				console.write("Invalid email address: no at sign\n");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			otherwise.apply(address, body);
		}
	}

}
