package com.idsd.clinicmanagement.Repository;

import com.idsd.clinicmanagement.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Recherche des patients par nom
    List<Patient> findByNomContains(String nom);
}
