package pl.fus.doctor_manager.Doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fus.doctor_manager.Hospital.Hospital;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String street;
    private String postcode;
    @OneToOne(mappedBy = "address", cascade = {CascadeType.ALL})
    @JsonIgnore
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Doctor doctor;
    @OneToOne(mappedBy = "address", cascade = {CascadeType.ALL})
    @JsonIgnore
    private Hospital hospital;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
