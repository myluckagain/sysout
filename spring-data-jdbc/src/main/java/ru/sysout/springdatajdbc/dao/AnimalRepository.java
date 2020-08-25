package ru.sysout.springdatajdbc.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.springdatajdbc.mapper.AnimalMapper;
import ru.sysout.springdatajdbc.model.Animal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Repository
public class AnimalRepository  {

    private final NamedParameterJdbcTemplate jdbc;


    @Transactional
    public Animal getById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject("select * from animal where id=:id", params, new AnimalMapper());

    }

    @Transactional
    public Animal insert(Animal animal) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", animal.getName());
        SqlParameterSource paramSource = new MapSqlParameterSource(params);

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        jdbc.update("insert into animal (name) values (:name)", paramSource, holder);
        animal.setId(holder.getKey().longValue());

        return animal;
    }


    @Transactional
    public Animal update(Animal animal) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", animal.getName());
        jdbc.update("insert into animal (name) values (:name)", params);
        return animal;
    }


    @Transactional
    public List<Animal> getAll() {

        return jdbc.query("select * from animal", new AnimalMapper());
    }
}
