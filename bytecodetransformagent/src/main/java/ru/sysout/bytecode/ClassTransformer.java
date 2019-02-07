package ru.sysout.bytecode;

import javassist.ClassPool;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer {

    private ClassPool pool= ClassPool.getDefault();



    public byte[] transform(ClassLoader loader,
                            String className,
                            Class classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            pool.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
            CtClass cclass = pool.get(className.replaceAll("/", "."));

            if (!cclass.getName().startsWith("ru.sysout.")) {
                return null;
            }

            for (CtMethod currentMethod : cclass.getDeclaredMethods()) {
                Log annotation = (Log) currentMethod.getAnnotation(Log.class);
                if (annotation != null) {
                    currentMethod.insertBefore("{System.out.println(\"" + annotation.message() + "\");}");
                }
            }

            return cclass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
