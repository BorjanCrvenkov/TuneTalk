package finki.bazi.tunetalk.model.exceptions;

public class UsernameCantContainAdminException extends RuntimeException{

    public UsernameCantContainAdminException() {
        super("Username can't contain any variation of the word admin.");
    }
}