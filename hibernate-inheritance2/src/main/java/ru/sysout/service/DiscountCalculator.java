package ru.sysout.service;

public interface DiscountCalculator<T> {
    double calculate(T customer);
    Class<T> getClazz();
}
