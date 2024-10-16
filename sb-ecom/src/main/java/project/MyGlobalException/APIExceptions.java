package project.MyGlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class APIExceptions extends RuntimeException{
    public APIExceptions(String message) {
        super(message);
    }

    public APIExceptions() {
    }

    private static final long serialVersionUID = 1L;


}


