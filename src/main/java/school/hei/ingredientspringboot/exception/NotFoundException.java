package school.hei.ingredientspringboot.exception;

/**
 * Exception levée quand une ressource est introuvable (→ HTTP 404).
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
