package finki.bazi.tunetalk.model.exceptions;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException() {
        super("Email is not valid. Email needs to contain @ and .com");
    }
}