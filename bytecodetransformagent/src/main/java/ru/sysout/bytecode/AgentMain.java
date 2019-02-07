package ru.sysout.bytecode;

import java.lang.instrument.Instrumentation;

public class AgentMain {

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Classes loaded: " + instrumentation.getAllLoadedClasses().length);

        instrumentation.addTransformer(new ClassTransformer());

    }
}
