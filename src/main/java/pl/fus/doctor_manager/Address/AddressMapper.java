package pl.fus.doctor_manager.Address;

import org.springframework.stereotype.Service;

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

    public AddressDto map(Address address) {
        AddressDto dto = new AddressDto();
        dto.setPostcode(address.getPostcode());
        dto.setStreet(address.getStreet());
        dto.setLocation(address.getLocation());
        dto.setDoctor(address.getDoctor());
        dto.setHospital(address.getHospital());
        return dto;
    }

}
