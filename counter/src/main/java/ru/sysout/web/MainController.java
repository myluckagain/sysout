package ru.sysout.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sysout.dao.service.HitService;
import ru.sysout.dto.HitsDto;

@RestController
public class MainController {
    @Autowired
    private HitService hitService;

    @GetMapping("/")
    public HitsDto main() {
        HitsDto hitsDTO = hitService.updateAndReturnCount();
        System.out.println(hitsDTO.getCount());
       return hitsDTO;
    }
}
