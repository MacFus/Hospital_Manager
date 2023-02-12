package pl.fus.doctor_manager.Hospital;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fus.doctor_manager.Address.AddressDto;
import pl.fus.doctor_manager.Doctor.DoctorDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HospitalDto {
    private Long id;
    private String name;
    private AddressDto address;
    @JsonIgnore
    private List<DoctorDto> doctorList;
}
