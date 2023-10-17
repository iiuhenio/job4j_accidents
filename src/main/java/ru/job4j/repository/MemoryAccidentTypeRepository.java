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

    public MemoryAccidentTypeRepository() {
        accidentTypes.put(1, new AccidentType(1, "Две Машины"));
        accidentTypes.put(2, new AccidentType(2, "Машина и человек"));
        accidentTypes.put(3, new AccidentType(3, "Машина и велосипед"));
    }

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