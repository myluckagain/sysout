package ru.sysout;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.dto.CityDto;
import ru.sysout.dto.DistrictDto;
import ru.sysout.dto.ShopDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class AppTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void shouldUseResultTransformer() {

        List<CityDto> cityDtos = em.createQuery(" select c.id as c_id, c.name as c_name,\n" +

                "  s.id as s_id,\n" +
                "  s.name as s_name,\n" +
                "  d.id as d_id,\n" +
                "  d.name as d_name\n" +
                "  from City c left join c.shops s join c.districts d \n" +
                "                   order by c.id")
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new CityDtoResultTransformer())
                .getResultList();


        Assertions.assertEquals(5, cityDtos.size());

        List shops = cityDtos.get(0).getShopDtoList();
        List districts = cityDtos.get(0).getDistrictDtoList();
        Assertions.assertEquals(2, shops.size());
        Assertions.assertEquals(2, districts.size());
    }

    @Test
    public void shouldUseStream() {

        Stream<Tuple> resultStream = em.createQuery(" select c.id as c_id, c.name as c_name,\n" +
                "   s.id as s_id,\n" +
                "   s.name as s_name,\n" +
                " d.id as d_id,\n" +
                " d.name as d_name\n" +
                "      from City c left join c.shops s join c.districts d \n" +
                "                   order by c.id", Tuple.class)
                .getResultStream();

        Map<Long, CityDto> cityDtoMap = new LinkedHashMap<>();

        List<CityDto> cityDtos = resultStream
                .map(tuple -> {
                    CityDto cityDto = cityDtoMap.computeIfAbsent(tuple.get("c_id", Long.class),
                            id -> new CityDto(
                                    tuple.get("c_id", Long.class),
                                    tuple.get("c_name", String.class)
                            )
                    );
                    if (tuple.get("d_id") != null) {
                        DistrictDto districtDto = new DistrictDto(
                                tuple.get("d_id", Long.class),
                                tuple.get("d_name", String.class));

                        if (!cityDto.getDistrictDtoList().contains(districtDto))
                            cityDto.getDistrictDtoList().add(districtDto);
                    }
                    if (tuple.get("s_id") != null) {
                        ShopDto shopDto = new ShopDto(
                                tuple.get("s_id", Long.class),
                                tuple.get("s_name", String.class));

                        if (!cityDto.getShopDtoList().contains(shopDto))
                            cityDto.getShopDtoList().add(shopDto);
                    }
                    return cityDto;
                })
                .distinct()
                .collect(Collectors.toList());


        Assertions.assertEquals(5, cityDtos.size());
        List shops = cityDtos.get(0).getShopDtoList();
        List districts = cityDtos.get(0).getDistrictDtoList();
        Assertions.assertEquals(2, shops.size());
        Assertions.assertEquals(2, districts.size());
    }
}

