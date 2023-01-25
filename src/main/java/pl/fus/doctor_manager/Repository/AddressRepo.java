package pl.fus.doctor_manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fus.doctor_manager.Entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
