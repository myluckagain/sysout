package ru.sysout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    public static final String ID_ALIAS = "c_id";
    public static final String NAME_ALIAS = "c_name";

    private long id;
    private String name;
    private List<DistrictDto> districtDtoList=new ArrayList<>();
    private List<ShopDto> shopDtoList=new ArrayList<>();


    public CityDto(long id, String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDto cityDto = (CityDto) o;
        return id == cityDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
