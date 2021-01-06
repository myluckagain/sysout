package ru.sysout.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TicketController {



    @GetMapping("/")
    public String ticket(){
        Random rand = new Random();
        return "ticket #"+rand.nextInt(1000);
    }
}
