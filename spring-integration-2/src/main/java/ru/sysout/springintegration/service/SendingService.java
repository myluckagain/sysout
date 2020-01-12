package ru.sysout.springintegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static org.springframework.integration.IntegrationMessageHeaderAccessor.CORRELATION_ID;

@Service
public class SendingService {
    @Autowired
    private MessageChannel input1;

    @Autowired
    private MessageChannel input2;

    @Autowired
    private MessageChannel input3;

    @Autowired
    private MessageChannel input4;

    @Autowired
    private MessageChannel input5;

    @Autowired
    private MessageChannel input6;

    @Autowired
    private MessageChannel input7;

    public void send1() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        this.input1.send(MessageBuilder.withPayload(list).build());

    }

    public void send2() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        for (int i = 0; i < list.size(); i++) {
            this.input2.send(MessageBuilder.withPayload(list.get(i)).build());
        }
    }

    public void send3() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        for (int i = 0; i < list.size(); i++) {
            this.input3.send(MessageBuilder.withPayload(list.get(i)).build());
        }
    }

    public void send4() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        for (int i = 0; i < list.size(); i++) {
            this.input4.send(MessageBuilder.withPayload(list.get(i)).build());
        }
    }

    public void send5() {
        input5.send(MessageBuilder.withPayload("a").setHeader(CORRELATION_ID, 1).build());
        input5.send(MessageBuilder.withPayload("b").setHeader(CORRELATION_ID, 1).build());
        input5.send(MessageBuilder.withPayload("c").setHeader(CORRELATION_ID, 2).build());
        input5.send(MessageBuilder.withPayload("d").setHeader(CORRELATION_ID, 2).build());
        input5.send(MessageBuilder.withPayload("e").setHeader(CORRELATION_ID, 2).build());
        input5.send(MessageBuilder.withPayload("f").setHeader(CORRELATION_ID, 2).build());
        input5.send(MessageBuilder.withPayload("g").setHeader(CORRELATION_ID, 2).build());
        input5.send(MessageBuilder.withPayload("h").setHeader(CORRELATION_ID, 2).build());
    }

    public void send6() throws InterruptedException {
        input6.send(MessageBuilder.withPayload("a").setHeader(CORRELATION_ID, 1).build());
        input6.send(MessageBuilder.withPayload("b").setHeader(CORRELATION_ID, 1).build());

        Thread.sleep(1000);

        input6.send(MessageBuilder.withPayload("c").setHeader(CORRELATION_ID, 1).build());
        input6.send(MessageBuilder.withPayload("d").setHeader(CORRELATION_ID, 1).build());
        input6.send(MessageBuilder.withPayload("e").setHeader(CORRELATION_ID, 1).build());

        Thread.sleep(1000);

        input6.send(MessageBuilder.withPayload("f").setHeader(CORRELATION_ID, 1).build());
        input6.send(MessageBuilder.withPayload("g").setHeader(CORRELATION_ID, 1).build());
        input6.send(MessageBuilder.withPayload("h").setHeader(CORRELATION_ID, 1).build());

        Thread.sleep(1000);
    }

    public void send7() throws InterruptedException {
        input7.send(MessageBuilder.withPayload("a").setHeader(CORRELATION_ID, 1).build());
        input7.send(MessageBuilder.withPayload("b").setHeader(CORRELATION_ID, 1).build());

        Thread.sleep(1000);

        input7.send(MessageBuilder.withPayload("c").setHeader(CORRELATION_ID, 1).build());
        input7.send(MessageBuilder.withPayload("d").setHeader(CORRELATION_ID, 1).build());
        input7.send(MessageBuilder.withPayload("e").setHeader(CORRELATION_ID, 1).build());

        Thread.sleep(1000);

        input7.send(MessageBuilder.withPayload("f").setHeader(CORRELATION_ID, 1).build());
        input7.send(MessageBuilder.withPayload("g").setHeader(CORRELATION_ID, 1).build());
        input7.send(MessageBuilder.withPayload("h").setHeader(CORRELATION_ID, 1).build());

        Thread.sleep(1000);
    }


}
