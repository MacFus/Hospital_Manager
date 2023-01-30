package pl.fus.doctor_manager.DtoMapper;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.DTO.AddressDto;
import pl.fus.doctor_manager.DTO.DoctorDto;
import pl.fus.doctor_manager.Entity.Address;
import pl.fus.doctor_manager.Entity.Doctor;

@Service
public class DoctorMapper {
    private final AddressMapper addressMapper;

    public DoctorMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    //wyjebałem ustawianie ID w obu mapperach
    public Doctor map(DoctorDto doctor) {
        Doctor doc = new Doctor();
        AddressDto addressDto = doctor.getAddress();
        doc.setAddress(addressMapper.map(addressDto));
        doc.setExpertise(doctor.getExpertise());
        doc.setFirstName(doctor.getFirstName());
        doc.setLastName(doctor.getLastName());
        return doc;
    }

    public DoctorDto map(Doctor doctor) {
        DoctorDto doc = new DoctorDto();
//        doc.setId(doctor.getId());
        doc.setFirstName(doctor.getFirstName());
        doc.setLastName(doctor.getLastName());
        doc.setExpertise(doctor.getExpertise());
        Address address = doctor.getAddress();
        doc.setAddress(addressMapper.map(address));
        doc.setHospitalId(doctor.getHospital().getId());
        return doc;
    }
}
