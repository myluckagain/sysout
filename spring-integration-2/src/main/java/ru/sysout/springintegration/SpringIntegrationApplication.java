package ru.sysout.springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import ru.sysout.springintegration.service.SendingService;


@SpringBootApplication
public class SpringIntegrationApplication {

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext ctx = SpringApplication.run(SpringIntegrationApplication.class, args);

        DirectChannel outputChannel = ctx.getBean("outputChannel", DirectChannel.class);
        SendingService service = ctx.getBean(SendingService.class);

        outputChannel.subscribe(message -> System.out.println("OUTPUT CHANNEL: " + message.getPayload()));

        // service.send1();
        // service.send2();
        // service.send3();
        // service.send4();
        // service.send5();
        //  service.send6();
        service.send7();

        ctx.close();
    }


}
