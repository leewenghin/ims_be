package com.example.ims.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PaginationRequest extends PaginationBaseRequest {

    @Override
    @Min(value = 1, message = "page must be at least 1")
    public Integer getPage() {
        return super.getPage();
    }

    @Override
    @Min(value = 1, message = "size must be at least 1")
    @Max(value = 100, message = "size must not exceed 100")
    public Integer getSize() {
        return super.getSize();
    }
}
