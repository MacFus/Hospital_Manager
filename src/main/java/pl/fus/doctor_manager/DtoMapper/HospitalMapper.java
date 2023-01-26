package pl.fus.doctor_manager.DtoMapper;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.DTO.HospitalDto;
import pl.fus.doctor_manager.Entity.Hospital;

@Service
public class HospitalMapper {

    public Hospital map(HospitalDto dto){
        Hospital hospital = new Hospital();
        hospital.setId(dto.getId());
        hospital.setName(dto.getName());
        hospital.setAddress(dto.getAddress());
        hospital.setDoctorList(dto.getDoctorList());
        return hospital;
    }

    public HospitalDto map(Hospital hosp){
        HospitalDto dto = new HospitalDto();
        dto.setId(hosp.getId());
        dto.setName(hosp.getName());
        dto.setAddress(hosp.getAddress());
        dto.setDoctorList(hosp.getDoctorList());
        return dto;
    }

}
