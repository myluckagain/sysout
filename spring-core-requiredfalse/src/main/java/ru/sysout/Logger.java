package ru.sysout;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${logger:true}")
public class Logger {
    public void log(String message){
        System.out.println("log");
    }
}
