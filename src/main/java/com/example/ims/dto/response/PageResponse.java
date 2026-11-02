package com.example.ims.dto.response;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T> list;

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("last_page")
    private Integer lastPage;

    @JsonProperty("showing_from")
    private Integer showingFrom;

    @JsonProperty("showing_to")
    private Integer showingTo;

    @JsonProperty("total")
    private Integer total;

    public static <T, R> PageResponse<R> from(Page<T> page, Function<T, R> mapper) {
        List<R> list = page.getContent().stream().map(mapper).toList();

        int showingFrom = 0;
        int showingTo = 0;

        if (page.getNumberOfElements() > 0) {
            showingFrom = page.getNumber() * page.getSize() + 1;
            showingTo = showingFrom + page.getNumberOfElements() - 1;
        }

        return PageResponse.<R>builder()
                .list(list)
                .currentPage(page.getNumber() + 1)
                .lastPage(page.getTotalPages())
                .showingFrom(showingFrom)
                .showingTo(showingTo)
                .total((int) page.getTotalElements())
                .build();
    }

    public static <T> PageResponse<T> from(Page<T> page) {
        List<T> list = page.getContent();

        int showingFrom = 0;
        int showingTo = 0;

        if (page.getNumberOfElements() > 0) {
            showingFrom = page.getNumber() * page.getSize() + 1;
            showingTo = showingFrom + page.getNumberOfElements() - 1;
        }

        return PageResponse.<T>builder()
                .list(list)
                .currentPage(page.getNumber() + 1)
                .lastPage(page.getTotalPages())
                .showingFrom(showingFrom)
                .showingTo(showingTo)
                .total((int) page.getTotalElements())
                .build();
    }
}
