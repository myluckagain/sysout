package ru.sysout.springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.sysout.springintegration.service.EmailEmitterService;


@SpringBootApplication
public class SpringIntegrationApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringIntegrationApplication.class, args);

        EmailEmitterService emitterService = (EmailEmitterService) context.getBean(EmailEmitterService.class);
        emitterService.sendEmails(2);

    }


}
