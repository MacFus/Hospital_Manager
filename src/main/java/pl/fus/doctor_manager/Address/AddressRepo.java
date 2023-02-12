package pl.fus.doctor_manager.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fus.doctor_manager.Doctor.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
