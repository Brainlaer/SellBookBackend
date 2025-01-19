package com.analitrix.sellbook.dto.book;

import com.analitrix.sellbook.helpers.dto.RequestGetDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto extends RequestGetDto {
     private String isxn;
     private String title;
     private String author;
     private String category;
     private String editorial;
}
