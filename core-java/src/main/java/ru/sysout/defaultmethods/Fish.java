package ru.sysout.defaultmethods;

public class Fish implements Animal {

	@Override
	public String move() {
		return "swim";
	}

	@Override
	public String sleep() {
		return "fish sleeps";
	}
}
