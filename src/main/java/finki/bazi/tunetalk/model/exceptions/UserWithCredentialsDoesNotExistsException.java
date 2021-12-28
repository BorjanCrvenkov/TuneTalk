package finki.bazi.tunetalk.model.exceptions;

public class UserWithCredentialsDoesNotExistsException extends RuntimeException{

    public UserWithCredentialsDoesNotExistsException(String username,String password) {
        super("User with username: " + username + " and password: " + password + " does not exist");
    }
}
