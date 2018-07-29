package ru.sysout.springbootprofiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class ExampleTestBean {
	public ExampleTestBean() {
		System.out.println("creating example test bean");
	}
}
