package ru.job4j.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentRule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Перепишем класс для работы с базой.
 */
@Repository
@AllArgsConstructor
public class RuleJdbcTemplate {

    private JdbcTemplate jdbc;


    private final Map<Integer, AccidentRule> accidentRules = new HashMap<>();

    public RuleJdbcTemplate() {
        accidentRules.put(1, new AccidentRule(1, "Статья. 1"));
        accidentRules.put(2, new AccidentRule(2, "Статья. 2"));
        accidentRules.put(3, new AccidentRule(3, "Статья. 3"));
    }


    public AccidentRule save(AccidentRule rule) {
        jdbc.update("insert into rules (name) values (?)",
                rule.getName());
        return rule;
    }

    public List<AccidentRule> getAll() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    AccidentRule accidentRule = new AccidentRule();
                    accidentRule.setId(rs.getInt("id"));
                    accidentRule.setName(rs.getString("name"));
                    return accidentRule;
                });
    }


    public boolean deleteById(int id) {

        return false;
    }


    public Optional<AccidentRule> findById(int id) {

        return null;
    }




    public Set<AccidentRule> getByIds(Set<Integer> ids) {
        return null;
    }
}
