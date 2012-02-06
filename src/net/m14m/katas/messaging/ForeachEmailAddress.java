package net.m14m.katas.messaging;

public class ForeachEmailAddress implements Action {

	private Action validator;
	private String address;
	private String body;
	private SendEmail sendEmail;

	public ForeachEmailAddress run(Action otherwise) {
		this.validator = otherwise;
		return this;
	}

	@Override
	public void apply(String address, String body) {
		this.address = address;
		this.body = body;
		validator.apply(address, body);
		sendEmail.apply(address, body);
	}

	public void noErros(SendEmail sendEmail) {
		this.sendEmail = sendEmail;
	}
}
