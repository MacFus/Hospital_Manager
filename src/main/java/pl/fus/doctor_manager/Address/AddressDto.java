package pl.fus.doctor_manager.Address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fus.doctor_manager.Doctor.Doctor;
import pl.fus.doctor_manager.Hospital.Hospital;
@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
    @JsonIgnore
    private Long id;
    private String location;
    private String street;
    private String postcode;
    @JsonIgnore
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Doctor doctor;
    @JsonIgnore
    private Hospital hospital;
}
