package ru.sysout.switchoperator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SwitchOperatorUnitTest {
	private SwitchOperator s = new SwitchOperator();
	@Test
	public void whenCompareWithSwitch_thenOk() {

		assertEquals("Weekend!", s.exampleOfUSe());
	}

	@Test
	public void whenForgetBreak_thenGoFurther() {

		assertEquals("Invalid day?", s.forgetBreak());
	}
	
	@Test
	public void whenCompareStringObj_thenByEqual() {

		assertEquals("Time to work", s.compareStringObject());
	}
}
