package com.analitrix.sellbook.model.core.dto.book;

import com.analitrix.sellbook.model.core.dto.SortEnum;
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
     private SortEnum sort=SortEnum.ASC;
     private BookSortableColumnsEnum sortableColumn=BookSortableColumnsEnum.title;
}
