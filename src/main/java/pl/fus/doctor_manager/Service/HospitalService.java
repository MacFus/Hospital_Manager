package pl.fus.doctor_manager.Service;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.DTO.HospitalDto;
import pl.fus.doctor_manager.DtoMapper.HospitalMapper;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Repository.HospitalRepo;

import java.util.List;


@Service
public class HospitalService {
    private final HospitalRepo hospitalRepo;
    private final HospitalMapper hospitalMapper;

    public HospitalService(HospitalRepo hospitalRepo, HospitalMapper hospitalMapper) {
        this.hospitalRepo = hospitalRepo;
        this.hospitalMapper = hospitalMapper;
    }

    public Hospital save(Hospital hospital) {
        return hospitalRepo.save(hospital);
    }

    public List<HospitalDto> getAll() {
        return hospitalRepo.findAll().stream().map(hospitalMapper::map).toList();
    }
}
