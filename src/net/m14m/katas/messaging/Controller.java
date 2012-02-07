package net.m14m.katas.messaging;

public class Controller {

	private SendsEmail sendsEmail;
	private NoAtSignInAddressChecker invalidAddressChecker;

	public Controller check(NoAtSignInAddressChecker invalidAddressChecker) {
		this.invalidAddressChecker = invalidAddressChecker;
		return this;
	}

	public Controller onNoError(SendsEmail sendsEmail) {
		this.sendsEmail = sendsEmail;
		return this;
	}

	public void doYourThing(String to, String body) {
		if (invalidAddressChecker.valid(to)) {
			sendsEmail.sendMail(to, body);
		} else {
			invalidAddressChecker.writeError();
		}
	}

}
