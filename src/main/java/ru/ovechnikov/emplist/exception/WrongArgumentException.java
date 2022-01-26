package ru.ovechnikov.emplist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongArgumentException extends Throwable {
    public WrongArgumentException(String message) {
        super(message);
    }
}
