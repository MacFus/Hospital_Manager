package pl.fus.doctor_manager.Service;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.DTO.HospitalDto;
import pl.fus.doctor_manager.DtoMapper.HospitalMapper;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Repository.HospitalRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class HospitalService {
    private final HospitalRepo hospitalRepo;
    private final HospitalMapper hospitalMapper;

    public HospitalService(HospitalRepo hospitalRepo, HospitalMapper hospitalMapper) {
        this.hospitalRepo = hospitalRepo;
        this.hospitalMapper = hospitalMapper;
    }

    public HospitalDto save(HospitalDto hospitalDto) {
        Hospital hospital = hospitalMapper.map(hospitalDto);
        Hospital savedHospital = hospitalRepo.save(hospital);
        return hospitalMapper.map(savedHospital);
    }

    public List<HospitalDto> getAll() {
        return hospitalRepo.findAll().stream().map(hospitalMapper::map).toList();
    }

    public Optional<HospitalDto> getHospitalById(Long id) {
        return hospitalRepo.findById(id).map(hospitalMapper::map);
    }

    public Optional<HospitalDto> updateHospital(Long id, HospitalDto dto) {
        if (!hospitalRepo.existsById(id)) {
            return Optional.empty();
        }
        dto.setId(id);
        Hospital hospToUpdate = hospitalMapper.map(dto);
        Hospital savedHospital = hospitalRepo.save(hospToUpdate);
        return Optional.of(hospitalMapper.map(savedHospital));
    }



}
