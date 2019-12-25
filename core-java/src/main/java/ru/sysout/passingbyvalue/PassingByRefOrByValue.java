package ru.sysout.passingbyvalue;

public class PassingByRefOrByValue {

    void m(Cat cat) {
        cat.setName("Vasya");
    }

    void m1(Cat cat) {
        cat.setName("Vasya");
        cat=new Cat("Fifa"); //здесь в С++ поменялся бы адрес внешней cat, а у нас меняется адрес внутренней cat
        cat.setName("Kitty");
        System.out.println(cat.getName()); //Kitty
    }
}
