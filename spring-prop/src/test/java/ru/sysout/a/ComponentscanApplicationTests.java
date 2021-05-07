package ru.sysout;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.sysout.a.A;
import ru.sysout.b.B;

@SpringBootTest
class ComponentscanApplicationTests {

	@Autowired
	A a;

	@Autowired
	B b;

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void contextLoads() {
	}

	@Test
	void contextLoads2() {
	}

}
