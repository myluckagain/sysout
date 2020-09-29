package ru.sysout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    public static final String ID_ALIAS = "c_id";
    public static final String NAME_ALIAS = "c_name";

    private long id;
    private String name;
    private List<DistrictDto> districtDtoList=new ArrayList<>();

    public CityDto(
            Object[] tuples,
            Map<String, Integer> aliasToIndexMap) {

        this.id = ((Number) tuples[aliasToIndexMap.get(ID_ALIAS)]).longValue();
        this.name = tuples[aliasToIndexMap.get(NAME_ALIAS)].toString();
    }
}
