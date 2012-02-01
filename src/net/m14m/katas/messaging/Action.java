package net.m14m.katas.messaging;

public interface Action {

	void apply(String address, String body);

}
