package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentRule;
import ru.job4j.model.AccidentType;

import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class MemoryAccidentMem implements AccidentMem {

    private final AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private MemoryAccidentMem() {
        AccidentType accidentType = new AccidentType(1, "Две машины");
        AccidentRule accidentRule = new AccidentRule(2, "Статья. 2");
        Set<AccidentRule> rules = Set.of(accidentRule);

        save(new Accident(1, "Name1", "Text1", "Address1", accidentType, rules));
        save(new Accident(2, "Name2", "Text2", "Address2", accidentType, rules));
        save(new Accident(3, "Name3", "Text3", "Address3", accidentType, rules));
    }

    @Override
    public Accident save(Accident accident) {
        accident.setId(nextId.getAndIncrement());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public boolean deleteById(int id) {

        return accidents.remove(id) != null;
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (id, oldAccident)
                -> new Accident(oldAccident.getId(),
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType(),
                accident.getRules()))
                != null;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }
}