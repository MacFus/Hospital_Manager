package pl.fus.doctor_manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.fus.doctor_manager.Entity.Doctor;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    @Query(
            value = "SELECT * FROM Doctor WHERE hospital_id = :id ",
            nativeQuery = true)
    List<Doctor> findAllByHospitalId(@Param("id") Long id);
}
