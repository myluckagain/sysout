package ru.sysout.springintegration.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.sysout.springintegration.model.Animal;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
@Service
public class AnimalFromToEmailConverter {

    @Value("${mail.email}")
    private String email;

    public SimpleMailMessage createRandomAnimalEmail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("New animal");
        //генеририруем случайные строки вида cat0.9542215123310624, cat0.2964173089983424
        mailMessage.setText("cat" + Math.random());

        //сами себе высылаем, чтобы не заводить два ящика
        mailMessage.setTo(this.email);
        mailMessage.setFrom(this.email);

        return mailMessage;
    }

    public Animal animalFromEmail(Message message){
        String animalName="";
        try {
            animalName = message.getContent().toString();
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return new Animal(animalName);
    }
}
