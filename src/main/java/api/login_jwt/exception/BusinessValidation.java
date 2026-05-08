package api.login_jwt.exception;

import java.util.HashMap;
import java.util.Map;

public class BusinessValidation {

    private final Map<String, String> errors = new HashMap<>();

    public void addError(String field, String message) {
        errors.put(field, message);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void throwIfHasErrors(String message) {
        if (hasErrors()) {
            throw new BusinessException(message, errors);
        }
    }
}