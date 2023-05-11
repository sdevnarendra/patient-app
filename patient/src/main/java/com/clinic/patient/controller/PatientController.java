package com.clinic.patient.controller;

import com.clinic.patient.dto.QuickSearchDTO;
import com.clinic.patient.model.Patient;
import com.clinic.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping("/list")
    public Page<Patient> PatientList(@RequestBody QuickSearchDTO quickSearchDTO) {
        return service.listPatient(quickSearchDTO);
    }

    @GetMapping(value = "/{PatientId}")
    public Patient viewPatientView(@PathVariable Long PatientId) {
        return service.getPatient(PatientId);
    }


    // validated for entity requirement so spring error not come up
    @PostMapping
    public Patient savePatient(@RequestBody @Valid Patient patient) {
        return service.savePatient(patient);
    }

    @DeleteMapping(value = "/{PatientId}")
    public Boolean deletePatient(@PathVariable Long PatientId) {
        return service.deletePatient(PatientId);
    }

}
