package pl.fus.doctor_manager.Address;

import org.springframework.stereotype.Service;

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
