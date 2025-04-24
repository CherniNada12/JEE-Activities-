package com.idsd.clinicmanagement;
import com.idsd.clinicmanagement.entities.Patient;
import com.idsd.clinicmanagement.Repository.PatientRepository;
import org.springframework.data.repository.Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.Optional;
@SpringBootApplication
public class ClinicManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(PatientRepository repo) {
        return args -> {
            // Ajouter des patients
            repo.save(new Patient(null, "Alice", new Date(), false, 85));
            repo.save(new Patient(null, "Nada", new Date(), true, 24));
            repo.save(new Patient(null, "Amira ", new Date(), true, 25));

            // Consulter tous les patients
            System.out.println("Tous les patients:");
            repo.findAll().forEach(System.out::println);

            // Consulter un patient par ID
            Optional<Patient> patientOptional = repo.findById(1L);
            if (patientOptional.isPresent()) {
                Patient p = patientOptional.get();
                System.out.println("Patient id=1: " + p);

                // Mettre à jour le patient
                p.setScore(95);
                repo.save(p);
            } else {
                System.out.println("Patient avec id=1 non trouvé.");
            }

            // Chercher des patients par nom
            System.out.println("Patients contenant 'Al':");
            repo.findByNomContains("Al").forEach(System.out::println);

            // Supprimer un patient (id=2)
            if (repo.existsById(2L)) {
                repo.deleteById(2L);
                System.out.println("Patient avec id=2 supprimé.");
            } else {
                System.out.println("Patient avec id=2 non trouvé pour suppression.");
            }
        };
    }
}
