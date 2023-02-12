package pl.fus.doctor_manager.Address;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.Doctor.Address;
import pl.fus.doctor_manager.Address.AddressRepo;
@Service
public class AddressService {
    private AddressRepo addressRepo;

    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public Address saveAddress(Address address){
        return addressRepo.save(address);
    }
}
