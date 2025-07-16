package com.analitrix.sellbook.dtos.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDto {
    private Long isxn;
    private String title;
    private Long publicationDate;
    private Long units;
    private String editorial;
    private Long cost;
    private String author;
    private String image;
    private String categoryId;
}
