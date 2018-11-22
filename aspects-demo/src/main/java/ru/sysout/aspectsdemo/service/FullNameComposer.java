package ru.sysout.aspectsdemo.service;

import org.springframework.stereotype.Component;


@Component
public class FullNameComposer {

    
    public String composeFullName(String firstName, String lastName) {

        return firstName + lastName;
    }
}
