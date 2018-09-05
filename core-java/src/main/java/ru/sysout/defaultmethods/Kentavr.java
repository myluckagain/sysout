package ru.sysout.defaultmethods;

public class Kentavr implements Man, Animal{

	@Override
	public String move() {
		return "kentavr moves";
	}

	@Override
	public String sleep() {
		return Man.super.sleep();
	}

}
