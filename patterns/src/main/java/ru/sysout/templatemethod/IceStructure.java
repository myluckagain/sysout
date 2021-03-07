package ru.sysout.templatemethod;

public abstract class IceStructure {

    //template method
    public void build() {
        collectSnow();
        formSnow();
        fillWithWater();
        //hook
        additionalAction();
    }

    protected void collectSnow() {
        System.out.println("собрать снег");
    }

    abstract protected void formSnow();

    protected void fillWithWater() {
        System.out.println("залить водой");
    }

    //hook
    protected void additionalAction() {
    }
}
