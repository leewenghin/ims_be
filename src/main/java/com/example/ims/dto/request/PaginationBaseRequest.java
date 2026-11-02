package com.example.ims.dto.request;

import java.util.Map;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationBaseRequest {
    private static final Map<String, String> SORT_FIELD_MAPPING = Map.of(
            "occurred_at", "created_at");

    @Min(value = 0, message = "page must be at least 0")
    private Integer page;

    @Setter(AccessLevel.NONE)
    private Integer size;

    @Setter(AccessLevel.NONE)
    private String sortBy;

    @Setter(AccessLevel.NONE)
    @Pattern(regexp = "asc|desc", message = "Sort direction must be either asc or desc")
    private String sortDirection;

    public void setSize(String size) {
        if (size == null || size.isBlank()) {
            this.size = null;
            return;
        }
        this.size = Integer.valueOf(size);
    }

    public void setSort_by(String sortBy) {
        this.sortBy = SORT_FIELD_MAPPING.getOrDefault(sortBy, sortBy);
    }

    public void setSort_direction(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
