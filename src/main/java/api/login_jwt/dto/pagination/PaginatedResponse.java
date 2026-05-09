package api.login_jwt.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaginatedResponse<T> {

    private T items;
    private PaginationResponse pagination;
}