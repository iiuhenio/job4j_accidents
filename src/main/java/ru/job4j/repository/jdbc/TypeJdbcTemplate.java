package ru.job4j.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentRule;
import ru.job4j.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Перепишем класс для работы с базой.
 */
@Repository
@AllArgsConstructor
public class TypeJdbcTemplate {

    private JdbcTemplate jdbc;


    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    public TypeJdbcTemplate() {
        accidentTypes.put(1, new AccidentType(1, "Две Машины"));
        accidentTypes.put(2, new AccidentType(2, "Машина и человек"));
        accidentTypes.put(3, new AccidentType(3, "Машина и велосипед"));
    }


    public AccidentType save(AccidentType type) {
        jdbc.update("insert into types (name) values (?)",
                type.getName());
        return type;
    }

    public Collection<AccidentType> getAll() {
        return jdbc.query("select id, name from types",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }


    public boolean deleteById(int id) {

        return true;
    }


    public Optional<AccidentType> findById(int id) {

        return null;
    }



}
