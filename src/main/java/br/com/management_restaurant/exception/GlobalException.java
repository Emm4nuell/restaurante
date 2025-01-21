package br.com.management_restaurant.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handlerNotFoundException(NotFoundException exception, HttpServletRequest http){
        return fieldError(
                HttpStatus.NOT_FOUND.value(),
                "Nao localizado na base de dados.",
                exception.getMessage(),
                http.getRequestURI()
        );
    }

    public Map<String, Object> fieldError(int status, String error, String message, String path){
        Map<String, Object> config = new HashMap<>();
        var timestamp = LocalDateTime.now();
        config.put("timestamp", timestamp);
        config.put("status", status);
        config.put("error", error);
        config.put("message", message);
        config.put("path", path);

        log.error("ERROR:: timestamp: {}, status: {}, error: {}, message: {}, path: {}", timestamp, status, error, message, path);

        return config;
    }
}
