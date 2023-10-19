package ru.job4j.repository;

import ru.job4j.model.AccidentRule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface AccidentRuleRepository {

    AccidentRule save(AccidentRule rule);

    boolean deleteById(int id);

    Optional<AccidentRule> findById(int id);

    Collection<AccidentRule> findAll();

    Set<AccidentRule> getByIds(Set<Integer> ids);
}
