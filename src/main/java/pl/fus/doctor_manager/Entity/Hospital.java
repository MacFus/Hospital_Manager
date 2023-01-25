package pl.fus.doctor_manager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "address_id", unique = true)
    private Address address;
    @OneToMany(mappedBy = "hospital")
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
