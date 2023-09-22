package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleAccidentService implements AccidentService {

    private final AccidentMem accidentMem;


    public SimpleAccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    @Override
    public Accident save(Accident accident) {
        return accidentMem.save(accident);
    }


    @Override
    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        var isDeleted = accidentMem.deleteById(id);
        return isDeleted;
    }

    @Override
    public boolean update(Accident accident) {
        var isUpdated = accidentMem.update(accident);
        return isUpdated;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    @Override
    public Accident create(Accident accident) {
        return accidentMem.save(accident);

    }
}
