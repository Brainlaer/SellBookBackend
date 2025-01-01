package com.analitrix.sellbook.dto.book;

import com.analitrix.sellbook.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDtoPost {
    private Long isxn;
    private String title;
    private Long publicationDate;
    private Long units;
    private String editorial;
    private Long cost;
    private String author;
    private String image;
    private Category category;
}
