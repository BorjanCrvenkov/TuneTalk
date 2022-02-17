package finki.bazi.tunetalk.model.exceptions;

public class UserWithCredentialsDoesNotExistsException extends RuntimeException{

    public UserWithCredentialsDoesNotExistsException() {
        super("User with entered credentials does not exist");
    }
}
