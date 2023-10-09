package ru.job4j.service;

import ru.job4j.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeService {


    AccidentType save(AccidentType accidentType);

    boolean deleteById(int id);

    Optional<AccidentType> findById(int id);

    Collection<AccidentType> findAll();
}
