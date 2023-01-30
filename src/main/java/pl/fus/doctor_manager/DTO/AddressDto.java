package pl.fus.doctor_manager.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fus.doctor_manager.Entity.Doctor;
import pl.fus.doctor_manager.Entity.Hospital;
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
