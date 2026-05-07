package api.login_jwt.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DtoMessage {

    private String type;
    private String message;

    private List<String> details;

    private Map<String, String> fieldErrors;

    public DtoMessage() {
        this.type = "error";
        this.details = new ArrayList<>();
        this.fieldErrors = new HashMap<>();
    }

    public boolean hasErrors() {
        return !details.isEmpty() || !fieldErrors.isEmpty();
    }

    public void addDetail(String detail) {
        this.details.add(detail);
    }

    public void addFieldError(String field, String errorMessage) {
        this.fieldErrors.put(field, errorMessage);
    }

    public void setSuccess(String message) {
        this.type = "success";
        this.message = message;
    }

    public void setError(String message) {
        this.type = "error";
        this.message = message;
    }

    public void setWarning(String message) {
        this.type = "warning";
        this.message = message;
    }

    public void setException(String message) {
        this.type = "exception";
        this.message = message;
    }
}