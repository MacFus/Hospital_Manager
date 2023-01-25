package pl.fus.doctor_manager.Service;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Repository.AddressRepo;
import pl.fus.doctor_manager.Repository.DoctorRepo;
import pl.fus.doctor_manager.Repository.HospitalRepo;

@Service
public class HospitalService {
    private final AddressRepo addressRepo;
    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;
    private final AddressService addressService;


    public HospitalService(AddressRepo addressRepo, DoctorRepo doctorRepo, HospitalRepo hospitalRepo, AddressService addressService) {
        this.addressRepo = addressRepo;
        this.doctorRepo = doctorRepo;
        this.hospitalRepo = hospitalRepo;
        this.addressService = addressService;
    }

    public Hospital save(Hospital hospital) {
        return hospitalRepo.save(hospital);
    }
}
