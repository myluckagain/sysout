package ru.sysout.defaultmethods;

public interface Man {
	default String sleep() {
		return ("man sleeps");
	}
}
