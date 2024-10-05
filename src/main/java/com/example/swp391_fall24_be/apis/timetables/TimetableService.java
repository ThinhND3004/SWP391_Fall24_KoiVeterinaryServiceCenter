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

    public List<Timetable> doFindByVeterianId(String veterianId){
        Optional<List<Timetable>> timetablesResult = repository.findByVeterianId(veterianId);
        return timetablesResult.orElse(null);
    }

    @Transactional
    public List<Timetable> doSave(String veterianId, List<Timetable> timetables){
        repository.deleteAllByVeterianId(veterianId);
        return repository.saveAll(timetables);
    }
}
