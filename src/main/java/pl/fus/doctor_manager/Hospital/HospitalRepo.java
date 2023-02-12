package pl.fus.doctor_manager.Hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fus.doctor_manager.Hospital.Hospital;

public interface HospitalRepo extends JpaRepository<Hospital, Long> {
}
