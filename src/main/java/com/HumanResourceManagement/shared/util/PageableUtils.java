package com.HumanResourceManagement.shared.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Builds a {@link Pageable} from the usual page/size/sortBy/direction query
 * params so every controller doesn't have to repeat this logic.
 */
public final class PageableUtils {

    private static final int MAX_PAGE_SIZE = 100;

    private PageableUtils() {
    }

    public static Pageable build(int page, int size, String sortBy, String direction) {
        int safePage = Math.max(page, 0);
        int safeSize = Math.min(Math.max(size, 1), MAX_PAGE_SIZE);
        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return PageRequest.of(safePage, safeSize, sort);
    }
}
