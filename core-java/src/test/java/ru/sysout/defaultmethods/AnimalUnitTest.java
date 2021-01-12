package ru.sysout.defaultmethods;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnimalUnitTest {
	private Cat cat = new Cat();
	private Fish fish = new Fish();
	private Kentavr kentavr=new Kentavr();

	@Test
	public void whenCatSleep_thenOk() {
		assertEquals("sleep", cat.sleep());
	}

	@Test
	public void whenFishSleep_thenOnItsOwn() {
		assertEquals("fish sleeps", fish.sleep());
	}
	
	@Test
	public void whenKentavrSleep_thenSpecifyWhose() {
		assertEquals("man sleeps", kentavr.sleep());
	}
}
