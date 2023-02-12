package pl.fus.doctor_manager.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.JsonNode;
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
    private AddressDto address;
    //    @
    @JsonIgnore
    private List<DoctorDto> doctorList;
}
