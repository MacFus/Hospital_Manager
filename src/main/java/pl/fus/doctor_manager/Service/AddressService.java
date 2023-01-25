package pl.fus.doctor_manager.Service;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.Entity.Address;
import pl.fus.doctor_manager.Repository.AddressRepo;
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
