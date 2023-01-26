package pl.fus.doctor_manager.Service;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.DTO.DoctorDto;
import pl.fus.doctor_manager.DtoMapper.DoctorMapper;
import pl.fus.doctor_manager.Entity.Doctor;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Repository.DoctorRepo;
import pl.fus.doctor_manager.Repository.HospitalRepo;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class DoctorService {
    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;
    private final DoctorMapper doctorMapper;


    public DoctorService(DoctorRepo doctorRepo, HospitalRepo hospitalRepo, DoctorMapper doctorMapper) {
        this.doctorRepo = doctorRepo;
        this.hospitalRepo = hospitalRepo;
        this.doctorMapper = doctorMapper;
    }

    public DoctorDto saveDoctor(DoctorDto doctor) {
        Doctor doc = doctorMapper.map(doctor);
        doc.setAddDate(LocalDateTime.now());
        doc.setHospital(hospitalRepo.findById(doctor.getHospitalId())
                .orElseThrow(NoSuchElementException::new));
        DoctorDto savedDoctor = doctorMapper.map(doctorRepo.save(doc));
        return savedDoctor;
    }
}
