package pl.fus.doctor_manager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String expertise;
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "address_id", unique = false)
    private Address address;
    private LocalDateTime addDate;
    //w tabeli doctor powstanie pole wskazujące na dany szpital
    @ManyToOne(optional = false)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", expertise='" + expertise + '\'' +
                ", address=" + address +
                ", addDate=" + addDate +
                ", hospital=" + hospital +
                '}';
    }
}
