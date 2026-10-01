package se.lexicon.ecommerce.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.ecommerce.dto.AddressResponse;
import se.lexicon.ecommerce.dto.CustomerRequest;
import se.lexicon.ecommerce.dto.CustomerResponse;
import se.lexicon.ecommerce.entity.Address;
import se.lexicon.ecommerce.entity.Customer;

@Component
public class CustomerMapper {

    public CustomerResponse toResponse(Customer customer) {
        Address address = customer.getAddress();

        AddressResponse addressResponse = new AddressResponse(
                address.getStreet(),
                address.getCity(),
                address.getZipCode()
        );

        String fullName = customer.getFirstName() + " " + customer.getLastName();

        return new CustomerResponse(
                customer.getId(),
                fullName,
                customer.getEmail(),
                addressResponse
        );
    }

    public Customer toEntity(CustomerRequest request) {
        Address address = new Address(
                request.street(),
                request.city(),
                request.zipCode()
        );

        return new Customer(
                request.firstName(),
                request.lastName(),
                request.email(),
                address
        );
    }
}