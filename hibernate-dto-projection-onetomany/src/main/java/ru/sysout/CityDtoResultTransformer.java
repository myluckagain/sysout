package ru.sysout;

import org.hibernate.transform.ResultTransformer;
import ru.sysout.dto.CityDto;
import ru.sysout.dto.DistrictDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CityDtoResultTransformer implements ResultTransformer {

    private Map<Long, CityDto> cityDTOMap = new LinkedHashMap<>();

    @Override
    public Object transformTuple(
            Object[] tuple,
            String[] aliases) {

        Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

        Long cityId= ((Number) tuple[aliasToIndexMap.get(CityDto.ID_ALIAS)]).longValue();

        CityDto cityDto = cityDTOMap.computeIfAbsent(
                cityId,
                id -> new CityDto(tuple, aliasToIndexMap)
        );

        cityDto.getDistrictDtoList().add(
                new DistrictDto(tuple, aliasToIndexMap)
        );

        return cityDto;
    }

    @Override
    public List transformList(List collection) {
        return new ArrayList(cityDTOMap.values());
    }

    public Map<String, Integer> aliasToIndexMap(
            String[] aliases) {

        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

        for (int i = 0; i < aliases.length; i++) {
            aliasToIndexMap.put(aliases[i], i);
        }

        return aliasToIndexMap;
    }
}