package ru.sysout.springactuator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {
	private static final String template = "Hello, %s!";

	@GetMapping("/hello")
	@ResponseBody
	public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
		return new Greeting(String.format(template, name));
	}
}
