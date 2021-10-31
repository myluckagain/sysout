package ru.sysout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    public static final String ID_ALIAS = "s_id";
    public static final String NAME_ALIAS = "s_name";

    private long id;
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopDto shopDto = (ShopDto) o;
        return id == shopDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
