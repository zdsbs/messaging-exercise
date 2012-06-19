package net.m14m.katas.messaging;

import java.util.ArrayList;
import java.util.List;

public class Controller {
	
	private final EmailAddresses emailAddresses;
	private final Body body;
	private List<ProcessingStep> processingSteps = new ArrayList<ProcessingStep>();
	
	public Controller(EmailAddresses emailAddresses, Body body) {
		this.emailAddresses = emailAddresses;
		this.body = body;
	}
	
	public Controller process(ProcessingStep processingStep) {
		processingSteps.add(processingStep);
		return this;
	}
	
	public void run() {
		boolean previousPassed = true;
		for (ProcessingStep processingStep : processingSteps) {
			if (processingStep.shortCircut(emailAddresses, body, previousPassed)) {
				continue;
			}
			previousPassed = processingStep.process(emailAddresses, body);
		}
	}
	
}
