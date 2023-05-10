package bg.softuni.pizzashop.repository;

import bg.softuni.pizzashop.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCityAndAndNeighborhoodAndStreetAndStreetNumber(String city, String neighborhood, String street, int streetNumber);
}
