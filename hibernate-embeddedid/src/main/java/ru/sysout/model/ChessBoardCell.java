package ru.sysout.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChessBoardCell {
    @EmbeddedId
    private ChessBoardCellKey chessBoardCellKey;
    private String color;
}
