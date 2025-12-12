package SomLopere.SomLopere.exception;

/**
 * Exception lancée quand une ressource n'existe pas (ex: auteur ou livre)
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
