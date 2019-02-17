package ru.sysout.lambdas;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SubsribeServiceTest {
    @Test
    public void whenGetAll_thenAll() {

        List<String> list = Arrays.asList("a.group1@dd.ru", "a2@dd.ru", "bgroup1@dd.ru");
        SubscribeService service = new SubscribeService(list);
        service.getEmails((email) -> true);

    }


    @Test
    public void whenGetA_thenA() {

        List<String> list = Arrays.asList("a.group1@dd.ru", "a2@dd.ru", "bgroup1@dd.ru");
        SubscribeService service = new SubscribeService(list);
        service.getEmails((email) -> email.startsWith("a"));

    }


    @Test
    public void givenWithoutLambda_whenGetA_thenA() {

        List<String> list = Arrays.asList("a.group1@dd.ru", "a2@dd.ru", "bgroup1@dd.ru");
        SubscribeService service = new SubscribeService(list);
        service.getEmails(new Predicate<String>() {
            @Override
            public boolean test(String email) {
                return email.startsWith("a");
            }
        });

    }

    @Test
    public void whenGetGroup1_thenGroup1() {

        List<String> list = Arrays.asList("a.group1@dd.ru", "a2@dd.ru", "bgroup1@dd.ru");
        SubscribeService service = new SubscribeService(list);
        service.getEmails((email) -> email.contains("group1"));
        Consumer<String> consumer= a->System.out.println(a);
        BinaryOperator<BigInteger> b=(f,d)->f.add(d);
    }

    @Test
    public void whenGetAGroup1_thenAGroup1() {

        List<String> list = Arrays.asList("a.group1@dd.ru", "a2@dd.ru", "bgroup1@dd.ru");
        SubscribeService service = new SubscribeService(list);
        service.getEmails(email -> email.startsWith("a") && email.contains("group1"));

    }

}

