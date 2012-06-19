package net.m14m.katas.messaging;

import java.util.ArrayList;
import java.util.List;

public class EmailAddressesComeBeforeLastArg implements EmailAddresses {
	private final List<String> emailAddresses;
	
	public EmailAddressesComeBeforeLastArg(String[] args) {
		this.emailAddresses = new ArrayList<String>();
		for (int i = 0; i < args.length - 1; i++) {
			emailAddresses.add(args[i]);
		}
	}
	
	@Override
	public List<String> getBadEmailAddresses() {
		List<String> badAddresses = new ArrayList<String>();
		for (String address : emailAddresses) {
			if (address.contains("@") == false) {
				badAddresses.add(address);
			}
		}
		return badAddresses;
	}
	
	@Override
	public List<String> getGoodEmailAddresses() {
		List<String> goodAddresses = new ArrayList<String>();
		for (String address : emailAddresses) {
			if (address.contains("@") == true) {
				goodAddresses.add(address);
			}
		}
		return goodAddresses;
	}
	
}
