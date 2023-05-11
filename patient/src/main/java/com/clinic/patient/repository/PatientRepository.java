package com.clinic.patient.repository;

import com.clinic.patient.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findOneById(Long id);

    @Query("select model from Patient model where CONCAT(model.firstName, ' ', model.lastName) like %:search% or id=:id")
    Page<Patient> findAllSearch(@Param("search")String search, @Param("id") Long id, Pageable pageable);

}
