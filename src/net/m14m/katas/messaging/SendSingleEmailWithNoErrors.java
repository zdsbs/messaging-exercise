package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class SendSingleEmailWithNoErrors implements ProcessingStep {
	
	private final Writer network;
	
	public SendSingleEmailWithNoErrors(Writer network) {
		this.network = network;
	}
	
	@Override
	public boolean process(EmailAddresses emailAddresses, Body body) {
		try {
			network.write("connect smtp\nTo: " + emailAddresses.getGoodEmailAddresses().get(0) + "\n\n" + body.getBody() + "\n\ndisconnect\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
	
	@Override
	public boolean shortCircut(EmailAddresses emailAddresses, Body body, boolean previousPassed) {
		if (!previousPassed || emailAddresses.getGoodEmailAddresses().size() > 1) {
			return true;
		}
		return false;
	}
	
}
