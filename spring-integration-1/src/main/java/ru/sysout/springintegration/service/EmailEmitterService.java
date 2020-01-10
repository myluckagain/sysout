package ru.sysout.springintegration.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmailEmitterService {
    private final MessageChannel sendMailChannel;
    private final AnimalFromToEmailConverter converter;

    public  EmailEmitterService(@Qualifier("sendMailChannel") MessageChannel sendMailChannel,
                                AnimalFromToEmailConverter converter){
        this.sendMailChannel=sendMailChannel;
        this.converter=converter;

    }


    public void sendEmails(int emailCount) {

        for (int i = 0; i < emailCount; i++) {
            Message message = MessageBuilder.withPayload(converter.createRandomAnimalEmail()).build();
            sendMailChannel.send(message);
        }
    }
}
