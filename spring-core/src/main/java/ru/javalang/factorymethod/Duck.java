package ru.javalang.factorymethod;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Duck")
public class Duck extends Animal {

}
