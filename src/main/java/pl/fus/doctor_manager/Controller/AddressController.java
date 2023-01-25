package pl.fus.doctor_manager.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fus.doctor_manager.Entity.Address;
import pl.fus.doctor_manager.Repository.AddressRepo;
import pl.fus.doctor_manager.Service.AddressService;

@Controller
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressRepo addressRepo, AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    ResponseEntity<Address> saveAddress(@RequestBody Address address){
        Address saveAddress = addressService.saveAddress(address);
        return ResponseEntity.ok().build();
    }
}
