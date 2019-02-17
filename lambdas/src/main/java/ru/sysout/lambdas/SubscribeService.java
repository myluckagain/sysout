package ru.sysout.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SubscribeService {
    List<String> emails;

    public SubscribeService(List emails) {
        this.emails = emails;
    }

    public List getAllEmails() {
        List<String> list = new ArrayList<>();
        list.addAll(emails);
        return emails;
    }

    public List getGroup1Emais() {
        List<String> list = new ArrayList<>();
        for (String email : emails) {
            if (email.contains(".group1")) {
                list.add(email);
            }
        }
        return list;
    }

    public List getAEmails() {
        List<String> list = new ArrayList<>();
        for (String email : emails) {
            if (email.startsWith("a")) {
                list.add(email);
            }
        }
        return list;
    }

    public List getGroup1AEmails() {
        List<String> list = new ArrayList<>();
        for (String email : emails) {
            if (email.startsWith("a") && email.contains("group1")) {
                list.add(email);
            }
        }
        return list;
    }

    public List getEmails(Predicate<String> filter) {
        List<String> list = new ArrayList<>();
        for (String email : emails) {
            if (filter.test(email)) {
                list.add(email);
            }
        }
        return list;
    }
}
