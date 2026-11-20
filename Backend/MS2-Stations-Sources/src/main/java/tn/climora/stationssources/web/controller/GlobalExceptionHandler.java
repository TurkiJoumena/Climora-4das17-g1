package tn.climora.stationssources.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tn.climora.stationssources.exception.BusinessRuleException;
import tn.climora.stationssources.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.Map;

// traduit les exceptions en reponses json avec le bon code http
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> notFound(ResourceNotFoundException e) {
        return corps(404, e.getMessage());
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> conflict(BusinessRuleException e) {
        return corps(409, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> validation(MethodArgumentNotValidException e) {
        String details = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " : " + f.getDefaultMessage())
                .reduce((a, b) -> a + ", " + b)
                .orElse("donnees invalides");
        return corps(400, details);
    }

    @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> badRequest(Exception e) {
        return corps(400, "requete invalide : " + e.getMessage());
    }

    private Map<String, Object> corps(int statut, String message) {
        return Map.of("statut", statut, "message", message, "horodatage", LocalDateTime.now().toString());
    }
}
