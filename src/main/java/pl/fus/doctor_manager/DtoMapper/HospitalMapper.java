package pl.fus.doctor_manager.DtoMapper;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.DTO.AddressDto;
import pl.fus.doctor_manager.DTO.DoctorDto;
import pl.fus.doctor_manager.DTO.HospitalDto;
import pl.fus.doctor_manager.Entity.Address;
import pl.fus.doctor_manager.Entity.Doctor;
import pl.fus.doctor_manager.Entity.Hospital;

import java.util.List;

@Service
public class HospitalMapper {
    private final DoctorMapper doctorMapper;
    private final AddressMapper addressMapper;

    public HospitalMapper(DoctorMapper doctorMapper, AddressMapper addressMapper) {
        this.doctorMapper = doctorMapper;
        this.addressMapper = addressMapper;
    }

    public Hospital map(HospitalDto dto) {
        Hospital hospital = new Hospital();
        hospital.setId(dto.getId());
        hospital.setName(dto.getName());
        hospital.setAddress(addressMapper.map(dto.getAddress()));
        List<DoctorDto> doctorList = dto.getDoctorList();
        hospital.setDoctorList(
                doctorList
                        .stream()
                        .map(doctorMapper::map)
                        .toList());
        return hospital;
    }

    public HospitalDto map(Hospital hosp) {
        HospitalDto dto = new HospitalDto();
        dto.setId(hosp.getId());
        dto.setName(hosp.getName());
        dto.setAddress(addressMapper.map(hosp.getAddress()));
        List<Doctor> doctorList = hosp.getDoctorList();
        dto.setDoctorList(doctorList.stream().map(doctorMapper::map).toList());
        return dto;
    }

}
