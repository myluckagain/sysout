package ru.sysout;

import org.hibernate.transform.ResultTransformer;
import ru.sysout.dto.CityDto;
import ru.sysout.dto.DistrictDto;
import ru.sysout.dto.ShopDto;

import java.util.*;

public class CityDtoResultTransformer implements ResultTransformer {

    private Map<Long, CityDto> cityDTOMap = new LinkedHashMap<>();

    @Override
    public Object transformTuple(
            Object[] tuples,
            String[] aliases) {

        List<String> aliasList = Arrays.asList(aliases);

        Map<String, Object> tupleMap = aliasList.stream()
                .collect(
                        HashMap::new, (m, a) -> m.put(a, tuples[aliasList.indexOf(a)]), HashMap::putAll);


        Long cityId = (Long) tupleMap.get(CityDto.ID_ALIAS);
        String cityName = (String) tupleMap.get(CityDto.NAME_ALIAS);
        CityDto cityDto = cityDTOMap.computeIfAbsent(
                cityId,
                id -> new CityDto(cityId, cityName)
        );
        if (tupleMap.get(ShopDto.ID_ALIAS) != null) {
            ShopDto shopDto = new ShopDto((Long) tupleMap.get(ShopDto.ID_ALIAS), (String) tupleMap.get(ShopDto.NAME_ALIAS));
            if (!cityDto.getShopDtoList().contains(shopDto))
                cityDto.getShopDtoList().add(shopDto);
        }
        if (tupleMap.get(DistrictDto.ID_ALIAS) != null) {
            DistrictDto districtDto = new DistrictDto((Long) tupleMap.get(DistrictDto.ID_ALIAS), (String) tupleMap.get(DistrictDto.NAME_ALIAS));
            if (!cityDto.getDistrictDtoList().contains(districtDto))
                cityDto.getDistrictDtoList().add(districtDto);
        }
        return cityDto;
    }

    @Override
    public List transformList(List collection) {
        return new ArrayList(cityDTOMap.values());
    }

}