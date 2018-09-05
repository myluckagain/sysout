package ru.sysout.defaultmethods;

public interface Animal {

	String move();

	default String sleep() {
		return ("sleep");
	}
}
