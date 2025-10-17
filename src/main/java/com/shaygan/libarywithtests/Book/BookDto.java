package com.shaygan.libarywithtests.Book;

import java.util.List;

public record BookDto(
        Long id,
        String title,
        Long locationId,
        List<Long> authorIds
) {
}
