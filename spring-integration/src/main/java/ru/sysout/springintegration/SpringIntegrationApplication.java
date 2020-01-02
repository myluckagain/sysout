package ru.sysout.springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import ru.sysout.springintegration.model.Animal;

@SpringBootApplication
public class SpringIntegrationApplication {
    @Bean
    DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @MessagingGateway
    public interface I {

        @Gateway(requestChannel = "animalFlow.input")
        void process(Animal animal);
    }

    // канал DirectChannel с именем animalFlow.input создается автоматически
    @Bean
    public IntegrationFlow animalFlow() {
        return flow -> flow.handle("aService", "process")
                .handle("bService", "process")
                .handle("cService", "process")
                .channel("outputChannel");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringIntegrationApplication.class, args);

        DirectChannel outputChannel = ctx.getBean("outputChannel", DirectChannel.class);
        // обработчик внутри subscribe выполнится как только закончится выполнение flow
        outputChannel.subscribe(System.out::println);
        // запускаем выполнение flow
        ctx.getBean(I.class).process(new Animal("cat"));

        // можно было запустить flow отправкой сообщения во входной канал input:
        // MessageChannel inputChannel = ctx.getBean("animalFlow.input", MessageChannel.class);
        // inputChannel.send(MessageBuilder.withPayload(new Animal("cat")).build());
        ctx.close();
    }
}
