package pl.fus.doctor_manager.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fus.doctor_manager.Entity.Address;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String expertise;
    private Address address;
    private Long hospitalId;
}
