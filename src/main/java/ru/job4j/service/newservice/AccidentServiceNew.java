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
    private final RuleJdbcTemplate accidentTypeRepository;
    private final TypeJdbcTemplate accidentRuleRepository;

    public void create(Accident accident) {
        accidentsRepository.save(accident);
    }


    public Optional<Accident> save(Accident accident, int typeId) {
        Optional<AccidentType> accidentType = accidentTypeRepository.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        return Optional.ofNullable(accidentsRepository.save(accident));
    }


    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        var isDeleted = accidentsRepository.deleteById(id);
        return isDeleted;
    }


    public boolean update(Accident accident, Set<Integer> ruleIds) {
        Optional<AccidentRule> accidentType = accidentTypeRepository.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        Set<AccidentRule> accidentRule = accidentRuleRepository.getByIds(ruleIds);
        accident.setRules(accidentRule);
        return accidentsRepository.update(accident);

    }


    public Optional<Accident> findById(int id) {
        return accidentsRepository.findById(id);
    }


    public Collection<Accident> findAll() {
        return accidentsRepository.getAll();
    }


}
