package ru.sysout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDto {
    public static final String ID_ALIAS = "d_id";
    public static final String NAME_ALIAS = "d_name";

    private long id;
    private String name;

    public DistrictDto(
            Object[] tuples,
            Map<String, Integer> aliasToIndexMap) {
        this.id = ((Number) tuples[aliasToIndexMap.get(ID_ALIAS)]).longValue();
        this.name = tuples[aliasToIndexMap.get(NAME_ALIAS)].toString();
    }
}
