package se.lexicon.ecommerce.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("A customer with email " + email + " already exists.");
    }
}