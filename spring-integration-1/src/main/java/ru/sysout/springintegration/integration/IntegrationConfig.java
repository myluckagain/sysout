package ru.sysout.springintegration.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.messaging.MessageChannel;
import ru.sysout.springintegration.model.Animal;
import ru.sysout.springintegration.service.AnimalFromToEmailConverter;

import javax.persistence.EntityManagerFactory;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@Configuration
public class IntegrationConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private AnimalFromToEmailConverter converter;
    @Value("${mail.email}")
    private String email;
    @Value("${mail.password}")
    private String password;

    @Bean("jpaOutputAdapterChannel")
    public MessageChannel jpaOutputAdapterChannel() {
        return new DirectChannel();
    }

    @Bean("sendMailChannel")
    public MessageChannel sendMailChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow mailListener() {
       /* внимание,  надо закодировать имя и пароль с URLEncoder.encode(),
        чтоб не было лишних @
        поэтому email@mail.ru превращается в email%40mail.ru*/
        return IntegrationFlows.from(Mail.imapInboundAdapter(
                "imaps://"
                        + URLEncoder.encode(this.email, Charset.defaultCharset())
                        + ":"
                        + URLEncoder.encode(this.password, Charset.defaultCharset())
                        + "@imap.mail.ru:993/inbox"
                ).javaMailProperties(p -> {
                    p.put("mail.debug", "false");
                    p.put("mail.imaps.ssl.trust", "*");
                }),
                e -> e.poller(Pollers.fixedDelay(1000).maxMessagesPerPoll(1)))
                .<javax.mail.Message>handle((payload, headers) -> (payload))
                .handle(converter, "animalFromEmail")
                .<Animal>filter(animal -> !animal.getName().startsWith("cat0.1"))
                .handle(Jpa.outboundAdapter(this.entityManagerFactory)
                                .entityClass(Animal.class)
                                .persistMode(PersistMode.PERSIST),
                        e -> e.transactional(true)).get();
    }

    //вспомогательный поток - сгенерируем данные, отправим Animals в почтовый ящик.
    //но можно вручную отправить пиьсма с текстом вида cat0.9542215123310624
    @Bean
    public IntegrationFlow sendMailFlow() {
        return IntegrationFlows.from("sendMailChannel")
                .handle(Mail.outboundAdapter("smtp.mail.ru")
                                .port(25)
                                .credentials(this.email, this.password)
                                .javaMailProperties(p -> {
                                    p.put("mail.debug", "true");
                                    p.put("mail.smtp.ssl.trust", "*");
                                    p.put("mail.smtp.starttls.enable", "true");
                                }),
                        e -> e.id("sendMailEndpoint"))
                .get();
    }
}
