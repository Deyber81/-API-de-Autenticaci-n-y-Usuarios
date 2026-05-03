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
    private List<String> messages;
    private Map<String, String> fieldErrors;

    public DtoMessage() {
        this.type = "error";
        this.messages = new ArrayList<>();
        this.fieldErrors = new HashMap<>();
    }

    public boolean hasErrors() {
        return !this.messages.isEmpty() || !this.fieldErrors.isEmpty();
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public void addFieldError(String field, String errorMessage) {
        this.fieldErrors.put(field, errorMessage);
    }

    public void setSuccess() {
        this.type = "success";
    }

    public void setWarning() {
        this.type = "warning";
    }

    public void setError() {
        this.type = "error";
    }

    public void setException() {
        this.type = "exception";
    }

}
