package api.login_jwt.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse<T> {
    @JsonUnwrapped
    private DtoMessage message = new DtoMessage();

    private T data;

}
