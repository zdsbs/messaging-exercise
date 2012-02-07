package net.m14m.katas.messaging;

import java.io.IOException;
import java.io.Writer;

public class NoAtSignInAddressChecker {

	private final Writer console;

	public NoAtSignInAddressChecker(Writer console) {
		this.console = console;
		// TODO Auto-generated constructor stub
	}

	public boolean valid(String to) {
		if (to.contains("@")) {
			return true;
		}
		return false;
	}

	public void writeError() {
		try {
			console.write("Invalid email address: no at sign\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
