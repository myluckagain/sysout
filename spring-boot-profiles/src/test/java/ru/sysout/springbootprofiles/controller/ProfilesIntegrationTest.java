package ru.sysout.springbootprofiles.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import ru.sysout.springbootprofiles.ExampleTestBean;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfilesIntegrationTest {

	@Autowired
	Environment environment;

	@Autowired
	ExampleTestBean testBean;

	@Test
	public void testSpringProfiles() {
		for (final String profileName : environment.getActiveProfiles()) {
			System.out.println("Currently active profile - " + profileName);
		}

		Assert.assertEquals("test", environment.getActiveProfiles()[0]);

		assertNotNull(testBean);

	}
}
