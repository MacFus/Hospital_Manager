package pl.fus.doctor_manager.DtoMapper;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.DTO.AddressDto;
import pl.fus.doctor_manager.Entity.Address;

@Service
public class AddressMapper {
    public Address map(AddressDto dto) {
        Address address = new Address();
        address.setPostcode(dto.getPostcode());
        address.setStreet(dto.getStreet());
        address.setLocation(dto.getLocation());
        address.setDoctor(dto.getDoctor());
        address.setHospital(dto.getHospital());
        return address;
    }

    AddressDto map(Address address) {
        AddressDto dto = new AddressDto();
        dto.setPostcode(address.getPostcode());
        dto.setStreet(address.getStreet());
        dto.setLocation(address.getLocation());
        dto.setDoctor(address.getDoctor());
        dto.setHospital(address.getHospital());
        return dto;
    }

}
