package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class MissingEmailBody implements Action {

	private final Writer console;
	private BadEmailAddress otherwise;

	public MissingEmailBody(Writer console) {
		this.console = console;
	}

	@Override
	public void apply(String address, String body) {
		if (body.isEmpty()) {
			try {
				console.write("Cannot send an email with no body.\n");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			otherwise.apply(address, body);
		}
	}

	public BadEmailAddress otherwise(BadEmailAddress otherwise) {
		this.otherwise = otherwise;
		return otherwise;
	}

}
