package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.AccidentType;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class MemoryAccidentTypeRepository implements AccidentTypeRepository {


    private final AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    @Override
    public AccidentType save(AccidentType accidentType) {
        accidentType.setId(nextId.getAndIncrement());
        accidentTypes.put(accidentType.getId(), accidentType);
        return accidentType;
    }

    @Override
    public boolean deleteById(int id) {
        return accidentTypes.remove(id) != null;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(accidentTypes.get(id));
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypes.values();
    }
}