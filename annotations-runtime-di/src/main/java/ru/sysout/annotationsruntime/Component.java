package ru.sysout.annotationsruntime;

public class Component {

    @Autowire
    private Service1 service1;
    @Autowire
    private Service2 service2;

    public Service1 getService1() {

        return service1;
    }

    public Service2 getService2() {

        return service2;
    }
}
