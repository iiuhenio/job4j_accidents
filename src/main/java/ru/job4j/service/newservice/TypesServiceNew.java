package ru.job4j.service.newservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentRule;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.jdbc.RuleJdbcTemplate;
import ru.job4j.repository.jdbc.TypeJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypesServiceNew {
    private final TypeJdbcTemplate typesRepository;

    public void create(AccidentType type) {

        typesRepository.save(type);
    }

    public AccidentType save(AccidentType accidentType) {
        return typesRepository.save(accidentType);
    }


    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        var isDeleted = typesRepository.deleteById(id);
        return isDeleted;
    }


    public Optional<AccidentType> findById(int id) {
        return typesRepository.findById(id);
    }


    public Collection<AccidentType> findAll() {
        return typesRepository.getAll();
    }
}
