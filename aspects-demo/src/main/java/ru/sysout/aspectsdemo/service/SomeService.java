package ru.sysout.aspectsdemo.service;

import org.springframework.stereotype.Service;

import ru.sysout.aspectsdemo.LogExecutionTime;

@Service
public class SomeService {
    @LogExecutionTime
    public void someMethod() throws InterruptedException {
        Thread.sleep(1000);
    }
}
