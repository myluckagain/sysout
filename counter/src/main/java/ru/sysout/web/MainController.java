package ru.sysout.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sysout.dao.service.HitService;
import ru.sysout.model.Hits;

@RestController
public class MainController {
    @Autowired
    private HitService hitService;

    @GetMapping("/")
    public Hits main() {
        Hits hits = hitService.updateAndReturnCount();
        System.out.println(hits.getCount());
        return hits;
    }
}
