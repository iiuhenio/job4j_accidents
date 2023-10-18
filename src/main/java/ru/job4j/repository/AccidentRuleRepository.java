package ru.job4j.repository;

import ru.job4j.model.AccidentRule;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRuleRepository {

    AccidentRule save(AccidentRule rule);

    boolean deleteById(int id);

    Optional<AccidentRule> findById(int id);

    Collection<AccidentRule> findAll();
}
