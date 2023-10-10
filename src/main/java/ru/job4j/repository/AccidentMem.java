package ru.job4j.repository;

import ru.job4j.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentMem {

    Accident save(Accident accident);

    boolean deleteById(int id);

    boolean update(Accident accident);

    Optional<Accident> findById(int id);

    Collection<Accident> findAll();

}