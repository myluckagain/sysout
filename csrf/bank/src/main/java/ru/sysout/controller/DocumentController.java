package ru.sysout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sysout.model.Document;

@Controller
public class DocumentController {
    @PostMapping("/add")
    public String add(Document document, Model model) {

        System.out.println(document.getId()+" "+document.getText()+" added");

        model.addAttribute("document", document);
        model.addAttribute("message", "добавлено");
        System.out.println("PostMapping /add");
        return "add";
    }

    @GetMapping("/add")
    public String get() {
        System.out.println("GetMapping /add");
        return "add";
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/add";
    }
}
