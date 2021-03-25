package ru.sysout;

import car.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({Car.class})
public class MainApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext=SpringApplication.run(MainApplication.class, args);
		Car car=applicationContext.getBean(Car.class);
	}

}
