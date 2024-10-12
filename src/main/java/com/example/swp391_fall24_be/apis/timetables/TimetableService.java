package com.example.swp391_fall24_be.apis.timetables;

import com.example.swp391_fall24_be.apis.profiles.ProfileEntity;
import com.example.swp391_fall24_be.apis.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TimetableService {
    @Autowired
    private TimetableRepository repository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<TimetableEntity> doFindByVeterianId(String veterianId){
        ProfileEntity profile = profileRepository.findByAccountId(veterianId);
        Optional<List<TimetableEntity>> timetablesResult = repository.findByProfileId(profile.getId());
        return timetablesResult.orElse(null);
    }

    @Transactional
    public List<TimetableEntity> doSave(String veterianId, List<TimetableEntity> timetables){
        ProfileEntity profile = profileRepository.findByAccountId(veterianId);
        repository.deleteAllByProfileId(profile.getId());
        return repository.saveAll(timetables);
    }
}
