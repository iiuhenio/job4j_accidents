package ru.job4j.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Перепишем класс для работы с базой.
 */
@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident save(Accident accident) {
        jdbc.update("insert into accidents (name) values (?)",
                accident.getName());
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name from accidents",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

    public boolean deleteById(int id) {
        jdbc.update("delete from accidents where id = ?", id);
        return true;
    }


    public boolean update(Accident accident) {
        jdbc.update("update accidents set name = ? where id = ?",
                accident.getName(),
                accident.getId());
        return true;
    }

    public Optional<Accident> findById(int id, Accident accident) {
        jdbc.update("DELETE * FROM accidents WHERE id = ?",
                accident.getId());
        return Optional.of(accident);
    }

}