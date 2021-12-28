package finki.bazi.tunetalk.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{

    public PasswordsDoNotMatchException() {
        super("Password and Repeated Password do not match.");
    }
}