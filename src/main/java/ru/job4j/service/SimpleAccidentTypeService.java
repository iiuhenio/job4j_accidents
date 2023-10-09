package ru.job4j.service;

import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Optional;

public class SimpleAccidentTypeService implements AccidentTypeService {

    private final AccidentTypeRepository accidentTypeRepository;

    public SimpleAccidentTypeService(AccidentTypeRepository accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }

    @Override
    public AccidentType save(AccidentType accidentType) {
        return accidentTypeRepository.save(accidentType);
    }

    @Override
    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        var isDeleted = accidentTypeRepository.deleteById(id);
        return isDeleted;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepository.findById(id);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypeRepository.findAll();
    }
}
