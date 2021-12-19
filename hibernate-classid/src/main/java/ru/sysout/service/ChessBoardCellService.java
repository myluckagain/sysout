package ru.sysout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sysout.dao.ChessBoardCellRepository;
import ru.sysout.model.ChessBoardCell;

@Service
public class ChessBoardCellService {
    @Autowired
    private ChessBoardCellRepository chessBoardCellRepository;

    void addChessBoardCell(ChessBoardCell chessBoardCell){
        chessBoardCellRepository.save(chessBoardCell);
    }
}
