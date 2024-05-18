package com.analitrix.sellbook.dto;

import com.analitrix.sellbook.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto{
    private Long isxn;
    private String title;
    private Long datePosted;
    private Long units;
    private String editorial;
    private double cost;
    private String author;
    private String image;
    private Category category;
}
