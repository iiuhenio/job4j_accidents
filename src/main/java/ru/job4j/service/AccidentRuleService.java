package ru.job4j.service;

import ru.job4j.model.AccidentRule;
import ru.job4j.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRuleService {


    AccidentRule save(AccidentRule accidentRule);

    boolean deleteById(int id);

    Optional<AccidentRule> findById(int id);

    Collection<AccidentRule> findAll();
}
