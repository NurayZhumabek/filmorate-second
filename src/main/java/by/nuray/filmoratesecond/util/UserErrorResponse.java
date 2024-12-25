package by.nuray.filmoratesecond.util;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserErrorResponse {
    public String message;
    public long timestamp;

    public UserErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
