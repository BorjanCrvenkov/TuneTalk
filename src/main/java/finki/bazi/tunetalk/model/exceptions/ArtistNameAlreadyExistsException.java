package finki.bazi.tunetalk.model.exceptions;

public class ArtistNameAlreadyExistsException extends RuntimeException{

    public ArtistNameAlreadyExistsException(String name) {
        super("Artist with name: " + name + " already exists.");
    }
}
