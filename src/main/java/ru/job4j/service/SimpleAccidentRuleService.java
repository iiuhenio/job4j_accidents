package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentRule;
import ru.job4j.repository.AccidentRuleRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleAccidentRuleService implements AccidentRuleService {

    private final AccidentRuleRepository accidentRuleRepository;

    public SimpleAccidentRuleService(AccidentRuleRepository accidentRuleRepository) {
        this.accidentRuleRepository = accidentRuleRepository;
    }

    @Override
    public AccidentRule save(AccidentRule accidentRule) {
        return accidentRuleRepository.save(accidentRule);
    }

    @Override
    public boolean deleteById(int id) {
        var ruleOptional = findById(id);
        if (ruleOptional.isEmpty()) {
            return false;
        }
        var isDeleted = accidentRuleRepository.deleteById(id);
        return isDeleted;
    }

    @Override
    public Optional<AccidentRule> findById(int id) {
        return accidentRuleRepository.findById(id);
    }

    @Override
    public Collection<AccidentRule> findAll() {
        return accidentRuleRepository.findAll();
    }
}
