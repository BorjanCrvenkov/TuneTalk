package finki.bazi.tunetalk.model.exceptions;

public class GenreNameAlreadyExistsException extends RuntimeException{

    public GenreNameAlreadyExistsException(String name) {
        super("Genre with name: " + name + " already exists.");
    }
}
