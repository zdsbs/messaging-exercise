package net.m14m.katas.messaging;

public class Runner {

	private SendEmail sendEmail;

	public Runner start(SendEmail sendEmail) {
		this.sendEmail = sendEmail;
		return this;
	}

	public void using(String[] args) {
		sendEmail.apply(args[0], args[1]);
	}

}
