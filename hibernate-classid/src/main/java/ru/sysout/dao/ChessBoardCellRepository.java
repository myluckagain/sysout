package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.ChessBoardCell;
import ru.sysout.model.ChessBoardCellKey;

public interface ChessBoardCellRepository extends JpaRepository<ChessBoardCell, ChessBoardCellKey> {
}
