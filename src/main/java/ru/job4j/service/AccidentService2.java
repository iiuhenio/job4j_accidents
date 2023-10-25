package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.List;

/**
 * Пропишем зависимости в сервисе.
 */
@Service
@AllArgsConstructor
public class AccidentService2 {
    private final AccidentJdbcTemplate accidentsRepository;

    public void create(Accident accident) {
        accidentsRepository.save(accident);
    }

    public Collection<Accident> getAll() {
        return accidentsRepository.getAll();
    }
}