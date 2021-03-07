package ru.sysout.templatemethod;

public class Snegurochka extends IceStructure {

    @Override
    protected void formSnow() {
        System.out.println("сформировать снегурочку");
    }

    @Override
    protected void additionalAction() {
        System.out.println("поставить на постамент");
    }
}
