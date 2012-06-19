package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class MultipleEmailAddressNeedsAtSigns implements ProcessingStep {
	
	private final Writer console;
	
	public MultipleEmailAddressNeedsAtSigns(Writer console) {
		this.console = console;
	}
	
	@Override
	public boolean process(EmailAddresses emailAddresses, Body body) {
		String addresses = "";
		for (String add : emailAddresses.getBadEmailAddresses()) {
			addresses = addresses + add + ",";
		}
		addresses = addresses.substring(0, addresses.length() - 1);//kill the last comma
		try {
			console.write("Invalid email addresses: " + addresses + "\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return false;
	}
	
	@Override
	public boolean shortCircut(EmailAddresses emailAddresses, Body body, boolean previousPassed) {
		if (emailAddresses.getBadEmailAddresses().size() > 1) {
			return false;
		}
		return true;
	}
	
}
