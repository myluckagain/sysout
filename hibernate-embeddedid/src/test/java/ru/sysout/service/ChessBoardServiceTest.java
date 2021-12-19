package ru.sysout.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import ru.sysout.dao.ChessBoardCellRepository;
import ru.sysout.model.ChessBoardCell;
import ru.sysout.model.ChessBoardCellKey;

import java.util.List;

@DataJpaTest
@Import(ChessBoardCellService.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class ChessBoardServiceTest {
    @Autowired
    ChessBoardCellService chessBoardCellService;

    @Autowired
    ChessBoardCellRepository chessBoardCellRepository;

    @BeforeEach
    void init(){
        ChessBoardCellKey A1=new ChessBoardCellKey(1, 'A');
        ChessBoardCell cell1=new ChessBoardCell(A1, "white");
        chessBoardCellService.addChessBoardCell(cell1);

        ChessBoardCellKey B2=new ChessBoardCellKey(2, 'B');
        ChessBoardCell cell2=new ChessBoardCell(B2, "white");
        chessBoardCellService.addChessBoardCell(cell2);
    }
    @Test
    void shouldGetUsers(){
     List<ChessBoardCell>  cells=chessBoardCellRepository.findAll();
        Assertions.assertEquals(2, cells.size());
    }
}
