package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentMem;
import ru.job4j.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleAccidentService implements AccidentService {

    private final AccidentMem accidentMem;
    private final AccidentTypeRepository accidentTypeRepository;
    public SimpleAccidentService(AccidentMem accidentMem, AccidentTypeRepository accidentTypeRepository) {
        this.accidentMem = accidentMem;
        this.accidentTypeRepository = accidentTypeRepository;
    }

    @Override
    public Optional<Accident> save(Accident accident, int typeId) {
        accident.setType(accidentTypeRepository.findById(typeId).get());
        return Optional.of(accidentMem.save(accident));
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
        accident.setType(accidentTypeRepository.findById(accident.getType().getId()).get());
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
