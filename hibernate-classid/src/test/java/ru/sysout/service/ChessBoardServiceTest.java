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
import java.util.Optional;

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

        ChessBoardCell cell1=new ChessBoardCell(1, 'A', "white");
        chessBoardCellService.addChessBoardCell(cell1);
        ChessBoardCell cell2=new ChessBoardCell(2, 'B', "white");
        chessBoardCellService.addChessBoardCell(cell2);
    }

    @Test
    void shouldGetCells(){
     List<ChessBoardCell>  cells=chessBoardCellRepository.findAll();
        Assertions.assertEquals(2, cells.size());
    }

    @Test
    void shouldGetCell(){
        ChessBoardCellKey id=new ChessBoardCellKey(1, 'A');
        Optional<ChessBoardCell>  cell=chessBoardCellRepository.findById(id);
        Assertions.assertTrue(cell.isPresent());
        Assertions.assertEquals(1, cell.get().getVertical());
    }
}
