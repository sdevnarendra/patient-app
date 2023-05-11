package com.clinic.patient.service;

import com.clinic.patient.dto.QuickSearchDTO;
import com.clinic.patient.model.Patient;
import com.clinic.patient.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PatientService {
    private PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Page<Patient> listPatient(QuickSearchDTO quickSearchDTO) {
        if (quickSearchDTO.getSearch() == null || quickSearchDTO.getSearch().length() == 0) {
            return repository.findAll(quickSearchDTO.getPageAble());
        } else {
            Long id = -1l;
            try {
                id = Long.parseLong(quickSearchDTO.getSearch());
            } catch (Exception e) {

            }

            return repository.findAllSearch(quickSearchDTO.getSearch(), id, quickSearchDTO.getPageAble());
        }
    }

    public Patient getPatient(Long id) {
        return repository.findOneById(id);
    }

    public Patient savePatient(Patient patient) {
        Patient entity = patient.getId() == null ? patient : repository.findOneById(patient.getId());

        if (entity.getId() == null) {
            entity.setCreated(LocalDateTime.now());
        } else {
            entity.setUpdated(LocalDateTime.now());
            BeanUtils.copyProperties(patient, entity);
        }
        return repository.save(entity);
    }

    public Boolean deletePatient(Long id) {
        repository.deleteById(id);
        return true;
    }

}
