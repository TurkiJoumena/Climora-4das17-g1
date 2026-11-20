package tn.climora.stationssources.exception;

// regle metier non respectee, traduite en 409 Conflict
public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String message) {
        super(message);
    }
}
