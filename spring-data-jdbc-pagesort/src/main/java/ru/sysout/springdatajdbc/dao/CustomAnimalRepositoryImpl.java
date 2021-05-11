package ru.sysout.springdatajdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.sysout.springdatajdbc.model.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomAnimalRepositoryImpl implements CustomAnimalRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<Animal> getAnimals(Pageable pageable) {
        String rowCountSql = "SELECT count(*) from animal";

        int count = jdbcTemplate.queryForObject(
                rowCountSql, Integer.class);

        String querySql = "select * from animal " +
                "LIMIT " + pageable.getPageSize() + " " +
                "OFFSET " + pageable.getOffset();
        List<Animal> animals = jdbcTemplate.query(querySql, new AnimalMapper());


        return new PageImpl<>(animals, pageable, count);
    }
}

class AnimalMapper implements RowMapper<Animal> {
    @Override
    public Animal mapRow(ResultSet resultSet, int i) throws SQLException {
        final int id = resultSet.getInt("id");
        final String name = resultSet.getString("name");
        return new Animal(id, name);
    }
}