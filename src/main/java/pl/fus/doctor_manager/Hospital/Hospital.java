package pl.fus.doctor_manager.Hospital;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fus.doctor_manager.Doctor.Address;
import pl.fus.doctor_manager.Doctor.Doctor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(cascade = {CascadeType.ALL}) //, orphanRemoval = true
    @JoinColumn(name = "address_id", unique = true)
    private Address address;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Doctor> doctorList = new ArrayList<>();

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", doctorList=" + doctorList +
                '}';
    }
}
