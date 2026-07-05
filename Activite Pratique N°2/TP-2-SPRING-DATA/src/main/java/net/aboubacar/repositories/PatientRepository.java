package net.aboubacar.repositories;

import net.aboubacar.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByMalade(boolean malade);
    List<Patient> findByNomContains(String keyword);
    List<Patient> findByScoreGreaterThan(int score);
}
