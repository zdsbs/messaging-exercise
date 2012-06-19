package net.m14m.katas.messaging;

public interface ProcessingStep {
	public boolean process(EmailAddresses emailAddresses, Body body);
	
	public boolean shortCircut(EmailAddresses emailAddresses, Body body, boolean previousPassed);
}
