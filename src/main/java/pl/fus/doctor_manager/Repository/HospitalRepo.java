package pl.fus.doctor_manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fus.doctor_manager.Entity.Hospital;

public interface HospitalRepo extends JpaRepository<Hospital, Long> {
}
