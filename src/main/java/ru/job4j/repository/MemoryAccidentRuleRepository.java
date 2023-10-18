package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.AccidentRule;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class MemoryAccidentRuleRepository implements AccidentRuleRepository {


    private final AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, AccidentRule> accidentRules = new HashMap<>();

    public MemoryAccidentRuleRepository() {
        accidentRules.put(1, new AccidentRule(1, "Статья. 1"));
        accidentRules.put(2, new AccidentRule(2, "Статья. 2"));
        accidentRules.put(3, new AccidentRule(3, "Статья. 3"));
    }

    @Override
    public AccidentRule save(AccidentRule rule) {
        rule.setId(nextId.getAndIncrement());
        accidentRules.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public boolean deleteById(int id) {
        return accidentRules.remove(id) != null;
    }

    @Override
    public Optional<AccidentRule> findById(int id) {
        return Optional.ofNullable(accidentRules.get(id));
    }

    @Override
    public Collection<AccidentRule> findAll() {
        return accidentRules.values();
    }
}
