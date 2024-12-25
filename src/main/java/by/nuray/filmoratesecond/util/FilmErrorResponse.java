package by.nuray.filmoratesecond.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmErrorResponse {
    private String message;
    private long timestamp;

    public FilmErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
