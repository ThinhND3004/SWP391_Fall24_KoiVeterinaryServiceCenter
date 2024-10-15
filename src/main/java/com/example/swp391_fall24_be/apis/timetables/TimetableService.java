package com.example.swp391_fall24_be.apis.timetables;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.profiles.ProfileEntity;
import com.example.swp391_fall24_be.apis.profiles.ProfileRepository;
import com.example.swp391_fall24_be.apis.timetables.DTOs.TimetableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimetableService {
    @Autowired
    private TimetableRepository repository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<TimetableEntity> doFindByVeterianId(AccountEntity veterian){
        ProfileEntity profile = profileRepository.findProfileEntityByAccount(veterian);
        Optional<List<TimetableEntity>> timetablesResult = repository.findByProfileId(profile.getId());
        return timetablesResult.orElse(null);
    }

    @Transactional
    public List<TimetableDTO> doSave(AccountEntity veterian, List<TimetableEntity> timetables){
        ProfileEntity profile = veterian.getProfile();
        repository.deleteAllByProfileId(profile.getId());
        List<TimetableEntity> timetableEntities = repository.saveAll(timetables);
        List<TimetableDTO> timetableDTOS = new ArrayList<>();

        for(TimetableEntity timetable : timetableEntities){
            timetableDTOS.add(timetable.toResponseDto());
        }
        return timetableDTOS;
    }
}
