package ru.sysout.annotationsruntime;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Tester {
    public static void main(String... args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Component a= new Component();
        Tester.inject(a);
        a.getService1().method();
        a.getService2().method();
    }

    public static void inject(Component component) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Field field : component.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowire.class)) {
                Constructor constructor = field.getType().getDeclaredConstructor();
                Object o=constructor.newInstance();
                boolean isAccessible = field.canAccess(component);
                field.setAccessible(true);
                field.set(component, o);
                field.setAccessible(isAccessible);
            }
        }
    }
}
