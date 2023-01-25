package pl.fus.doctor_manager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToOne(mappedBy = "address", cascade = {CascadeType.PERSIST})
    private Doctor doctor;
    @OneToOne(mappedBy = "address", cascade = {CascadeType.PERSIST})
    private Hospital hospital;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                ", doctor=" + doctor +
                ", hospital=" + hospital +
                '}';
    }
}
