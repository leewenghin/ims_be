package com.example.ims.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.ims.dto.request.PaginationBaseRequest;

public class PageUtils {
    private static final String DEFAULT_SORT = "id";

    public static Pageable adjustPageable(PaginationBaseRequest pagination, Class<?> entityClass) {
        int page = pagination.getPage() == null ? 0 : Math.max(pagination.getPage() - 1, 0);
        Sort sort = sort(pagination, entityClass);
        if (pagination.getSize() == null) {
            return PageRequest.of(0, Integer.MAX_VALUE, sort);
        }
        return PageRequest.of(page, pagination.getSize(), sort);
    }

    private static Sort sort(PaginationBaseRequest pagination, Class<?> entityClass) {
        String requested = pagination.getSortBy();
        String field = (requested == null || requested.isBlank()) ? DEFAULT_SORT : toCamelCase(requested);
        if (!fields(entityClass).contains(field)) {
            throw new IllegalArgumentException("Unknown sort field: " + requested);
        }
        String direction = pagination.getSortDirection();
        if (direction == null || direction.isBlank() || direction.equalsIgnoreCase("asc")) {
            return Sort.by(Sort.Direction.ASC, field);
        }
        if (direction.equalsIgnoreCase("desc")) {
            return Sort.by(Sort.Direction.DESC, field);
        }
        throw new IllegalArgumentException("sortDirection must be asc or desc");
    }

    private static Set<String> fields(Class<?> type) {
        Set<String> names = new HashSet<>();
        Class<?> current = type;
        while (current != null && current != Object.class) {
            for (Field field : current.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    names.add(field.getName());
                }
            }
            current = current.getSuperclass();
        }
        return names;
    }

    private static String toCamelCase(String value) {
        StringBuilder builder = new StringBuilder();
        boolean upper = false;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '_') {
                upper = true;
                continue;
            }
            builder.append(upper ? Character.toUpperCase(c) : c);
            upper = false;
        }
        return builder.toString();
    }
}
