package ru.sysout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String get() {
        return "index";
    }

    @GetMapping("/one")
    public String getOne() {
        return "one";
    }
}
