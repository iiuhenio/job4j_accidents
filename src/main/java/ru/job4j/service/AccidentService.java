package ru.job4j.service;

import ru.job4j.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentService {

    Optional<Accident> save(Accident accident, int typeId);

    boolean deleteById(int id);

    boolean update(Accident accident);

    Optional<Accident> findById(int id);

    Collection<Accident> findAll();

    Accident create(Accident accident);
}
