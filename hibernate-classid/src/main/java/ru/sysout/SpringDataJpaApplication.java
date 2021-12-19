package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sysout.service.ChessBoardCellService;

@SpringBootApplication
public class SpringDataJpaApplication {
    @Autowired
    private ChessBoardCellService chessBoardCellService;


    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }


}
