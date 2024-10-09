package com.example.swp391_fall24_be.apis.timetables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TimetableService {
    @Autowired
    private TimetableRepository repository;

    public List<TimetableEntity> doFindByVeterianId(String veterianId){
        Optional<List<TimetableEntity>> timetablesResult = repository.findByVeterinarianId(veterianId);
        return timetablesResult.orElse(null);
    }

    @Transactional
    public List<TimetableEntity> doSave(String veterianId, List<TimetableEntity> timetables){
        repository.deleteAllByVeterinarianId(veterianId);
        return repository.saveAll(timetables);
    }
}
