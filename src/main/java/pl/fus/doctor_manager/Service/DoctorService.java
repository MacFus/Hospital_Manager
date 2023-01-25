package pl.fus.doctor_manager.Service;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.Entity.Doctor;
import pl.fus.doctor_manager.Repository.DoctorRepo;

import java.time.LocalDateTime;

@Service
public class DoctorService {
    private final DoctorRepo doctorRepo;

    public DoctorService(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public Doctor saveDoctor(Doctor doctor){
        System.out.println(doctor);
        doctor.setAddDate(LocalDateTime.now());
        Doctor savedDoctor = doctorRepo.save(doctor);
        return savedDoctor;
    }
}
