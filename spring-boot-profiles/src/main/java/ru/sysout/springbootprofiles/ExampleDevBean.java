package ru.sysout.springbootprofiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class ExampleDevBean {
	public ExampleDevBean() {
		System.out.println("creating example dev bean");
	}
}
