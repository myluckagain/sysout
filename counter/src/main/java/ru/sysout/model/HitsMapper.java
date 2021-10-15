package ru.sysout.model;



import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HitsMapper implements RowMapper<Hits> {


    @Override
    public Hits mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            final long id = resultSet.getLong("id");
            final long count = resultSet.getLong("count");
            return new Hits(id, count);


    }
}