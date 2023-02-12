package pl.fus.doctor_manager.Doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fus.doctor_manager.Address.AddressDto;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto {
//    private Long id;
    private String firstName;
    private String lastName;
    private String expertise;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AddressDto address;
//    @JsonIgnore
    private Long hospitalId;
}
