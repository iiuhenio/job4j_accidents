package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentRule;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentMem;
import ru.job4j.repository.AccidentRuleRepository;
import ru.job4j.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SimpleAccidentService implements AccidentService {

    private final AccidentMem accidentMem;
    private final AccidentTypeRepository accidentTypeRepository;
    private final AccidentRuleRepository accidentRuleRepository;

    public SimpleAccidentService(AccidentMem accidentMem, AccidentTypeRepository accidentTypeRepository, AccidentRuleRepository accidentRuleRepository) {
        this.accidentMem = accidentMem;
        this.accidentTypeRepository = accidentTypeRepository;
        this.accidentRuleRepository = accidentRuleRepository;
    }

    @Override
    public Optional<Accident> save(Accident accident, int typeId) {
        Optional<AccidentType> accidentType = accidentTypeRepository.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        return Optional.ofNullable(accidentMem.save(accident));
    }

    @Override
    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        var isDeleted = accidentMem.deleteById(id);
        return isDeleted;
    }

    @Override
    public boolean update(Accident accident, Set<Integer> ruleIds) {
        Optional<AccidentType> accidentType = accidentTypeRepository.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        Set<AccidentRule> accidentRule = accidentRuleRepository.getByIds(ruleIds);
        accident.setRules(accidentRule);
        return accidentMem.update(accident);

    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    @Override
    public Accident create(Accident accident, Set<Integer> ruleIds) {
        Optional<AccidentType> accidentType = accidentTypeRepository.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        Set<AccidentRule> accidentRule = accidentRuleRepository.getByIds(ruleIds);
        accident.setRules(accidentRule);
        return accidentMem.save(accident);
    }
}
