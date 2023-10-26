package ru.job4j.service.newservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentRule;
import ru.job4j.repository.jdbc.RuleJdbcTemplate;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RulesServiceNew {
    private final RuleJdbcTemplate rulesRepository;

    public void create(AccidentRule rule) {

        rulesRepository.save(rule);
    }


    public boolean deleteById(int id) {
        var ruleOptional = findById(id);
        if (ruleOptional.isEmpty()) {
            return false;
        }
        var isDeleted = rulesRepository.deleteById(id);
        return isDeleted;
    }


    public Optional<AccidentRule> findById(int id) {
        return rulesRepository.findById(id);
    }


    public Collection<AccidentRule> findAll() {
        return rulesRepository.getAll();
    }


    public Set<AccidentRule> getByIds(Set<Integer> ids) {
        return rulesRepository.getByIds(ids);
    }
}
