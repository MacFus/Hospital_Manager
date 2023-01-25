package pl.fus.doctor_manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fus.doctor_manager.Entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
