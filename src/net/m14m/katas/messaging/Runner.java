package net.m14m.katas.messaging;

public class Runner {

	private Action startAction;

	public Runner start(Action startAction) {
		this.startAction = startAction;
		return this;
	}

	public void using(String[] args) {
		startAction.apply(args[0], args[1]);
	}

}
