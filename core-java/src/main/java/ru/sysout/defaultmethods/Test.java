package ru.sysout.defaultmethods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;

public class Test {

	public static void main(String[] args) {
		List<String> c = new ArrayList<>();
		c.add("v");
		c.add("d");
		c.forEach(System.out::println);
		for (String t : c) {
			System.out.println(t);
		}
		
		
	}

}
