package ru.job4j.service.newservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentRule;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentRuleRepository;
import ru.job4j.repository.AccidentTypeRepository;
import ru.job4j.repository.jdbc.AccidentJdbcTemplate;
import ru.job4j.repository.jdbc.RuleJdbcTemplate;
import ru.job4j.repository.jdbc.TypeJdbcTemplate;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentServiceNew {

    private final AccidentJdbcTemplate accidentsRepository;
    private final RuleJdbcTemplate accidentRuleRepository;
    private final TypeJdbcTemplate accidentTypeRepository;

    public void create(Accident accident) {

        accidentsRepository.save(accident);
    }


    public boolean deleteById(int id, Accident accident) {
        var fileOptional = findById(id, accident);
        if (fileOptional.isEmpty()) {
            return false;
        }
        var isDeleted = accidentsRepository.deleteById(id);
        return isDeleted;
    }


    public boolean update(Accident accident, Set<Integer> ruleIds) {
        return accidentsRepository.update(accident);
    }


    public Optional<Accident> findById(int id, Accident accident) {
        return accidentsRepository.findById(id, accident);
    }


    public Collection<Accident> findAll() {
        return accidentsRepository.getAll();
    }


}
