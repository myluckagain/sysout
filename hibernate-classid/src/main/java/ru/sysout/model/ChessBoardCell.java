package ru.sysout.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ChessBoardCellKey.class)
public class ChessBoardCell {
    @Id
    private int vertical;
    @Id
    private char horizontal;


    private String color;

    public ChessBoardCellKey getId() {
        return new ChessBoardCellKey(
                vertical,
                horizontal

        );
    }

    public void setId(ChessBoardCellKey id) {
        this.vertical = id.getVertical();
        this.horizontal = id.getHorizontal();

    }
}
