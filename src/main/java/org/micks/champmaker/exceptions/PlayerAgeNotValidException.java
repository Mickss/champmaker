package org.micks.champmaker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PlayerAgeNotValidException extends RuntimeException {
    public PlayerAgeNotValidException(String message) {
        super(message);
    }
}
