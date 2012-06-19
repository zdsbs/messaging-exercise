package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class SingleEmailAddressNeedsAtSigns implements ProcessingStep {
	
	private final Writer console;
	
	public SingleEmailAddressNeedsAtSigns(Writer console) {
		this.console = console;
	}
	
	@Override
	public boolean process(EmailAddresses emailAddresses, Body body) {
		String address = emailAddresses.getBadEmailAddresses().get(0);
		try {
			console.write("Invalid email address: " + address + "\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return false;
	}
	
	@Override
	public boolean shortCircut(EmailAddresses emailAddresses, Body body, boolean previousPassed) {
		if (emailAddresses.getBadEmailAddresses().size() == 1) {
			return false;
		}
		return true;
	}
}
