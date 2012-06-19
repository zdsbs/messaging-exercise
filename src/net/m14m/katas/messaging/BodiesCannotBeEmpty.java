package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class BodiesCannotBeEmpty implements ProcessingStep {
	
	private final Writer console;
	
	public BodiesCannotBeEmpty(Writer console) {
		this.console = console;
	}
	
	@Override
	public boolean process(EmailAddresses emailAddresses, Body body) {
		try {
			console.write("Cannot send an email with no body.\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
	
	@Override
	public boolean shortCircut(EmailAddresses emailAddresses, Body body, boolean previousPassed) {
		if (body.getBody().isEmpty()) {
			return false;
		}
		return true;
	}
	
}
