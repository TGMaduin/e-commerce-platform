package se.lexicon.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerce.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByZipCode(String zipCode);
}