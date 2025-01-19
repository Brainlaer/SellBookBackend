package com.analitrix.sellbook.dto.book;

import com.analitrix.sellbook.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookGetDto {
    private String id;
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
