package finki.bazi.tunetalk.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String username) {
        super("Artist with username: " + username + " already exists.");
    }
}
