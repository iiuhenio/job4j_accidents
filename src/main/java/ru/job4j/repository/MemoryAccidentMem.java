package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class MemoryAccidentMem implements AccidentMem {

    private final AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private MemoryAccidentMem() {
        save(new Accident(1, "Name1", "Text1", "Address1"));
        save(new Accident(2, "Name2", "Text2", "Address2"));
        save(new Accident(3, "Name3", "Text3", "Address3"));
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
                accident.getAddress()))
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
