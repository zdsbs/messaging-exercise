package net.m14m.katas.messaging;

public class BodyIsLastArg implements Body {
	private final String body;
	
	public BodyIsLastArg(String[] args) {
		body = args[args.length - 1];
	}
	
	@Override
	public String getBody() {
		return body;
	}
	
}
