package pl.fus.doctor_manager.DtoMapper;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.fus.doctor_manager.DTO.DoctorDto;
import pl.fus.doctor_manager.Entity.Address;
import pl.fus.doctor_manager.Entity.Doctor;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Repository.HospitalRepo;

import javax.management.InstanceNotFoundException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class DoctorMapper {
    private final HospitalRepo hospitalRepo;

    public DoctorMapper(HospitalRepo hospitalRepo) {
        this.hospitalRepo = hospitalRepo;
    }

    //wyjebałem ustawianie ID w obu mapperach
    public Doctor map(DoctorDto doctor) {
        Doctor doc = new Doctor();
        doc.setAddress(doctor.getAddress());
        doc.setExpertise(doctor.getExpertise());
        doc.setFirstName(doctor.getFirstName());
        doc.setLastName(doctor.getLastName());
        return doc;
    }

    public DoctorDto map(Doctor doctor) {
        DoctorDto doc = new DoctorDto();
        doc.setId(doctor.getId());
        doc.setFirstName(doctor.getFirstName());
        doc.setLastName(doctor.getLastName());
        doc.setExpertise(doctor.getExpertise());
        doc.setAddress(doctor.getAddress());
        doc.setHospitalId(doctor.getHospital().getId());
        return doc;
    }
}
