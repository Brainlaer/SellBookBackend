package com.analitrix.sellbook.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookPaginationDto {
    private int limit;
    private int offset;
    private boolean flatten;
    private Long isxn;
    private int title;
    private String author;
    private String editorial;
    private String category;
}
