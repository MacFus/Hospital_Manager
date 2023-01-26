package pl.fus.doctor_manager.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fus.doctor_manager.Entity.Address;
import pl.fus.doctor_manager.Entity.Doctor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HospitalDto {
    private Long id;
    private String name;
    private Address address;
//    @JsonManagedReference
    private List<Doctor> doctorList;
}
