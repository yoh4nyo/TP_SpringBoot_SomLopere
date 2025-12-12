package SomLopere.SomLopere.exception;

import java.time.LocalDateTime;

/**
 * Objet JSON renvoyé en cas d'erreur
 */
public class ApiError {

    private int status;           // code HTTP
    private String message;       // message d'erreur
    private LocalDateTime timestamp; // date/heure
    private String path;          // endpoint appelé

    public ApiError(int status, String message, LocalDateTime timestamp, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    // --- GETTERS / SETTERS ---

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
