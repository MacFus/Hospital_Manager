package pl.fus.doctor_manager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToOne(mappedBy = "address", cascade = {CascadeType.ALL})
    @JsonIgnore
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
