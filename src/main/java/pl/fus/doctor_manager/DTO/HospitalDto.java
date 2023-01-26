package pl.fus.doctor_manager.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.fus.doctor_manager.Entity.Address;
import pl.fus.doctor_manager.Entity.Doctor;

import java.util.List;

public class HospitalDto {
    private Long id;
    private String name;
    private Address address;
    @JsonIgnore
    private List<Doctor> doctorList;
}
