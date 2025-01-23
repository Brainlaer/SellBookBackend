package com.analitrix.sellbook.dtos.book;

import com.analitrix.sellbook.enums.BookSortableColumnsEnum;
import com.analitrix.sellbook.enums.SortEnum;
import com.analitrix.sellbook.dtos.common.PaginationDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto extends PaginationDto {
     private String isxn;
     private String title;
     private String author;
     private String category;
     private String editorial;
     private SortEnum sort=SortEnum.ASC;
     private BookSortableColumnsEnum sortableColumn=BookSortableColumnsEnum.title;
}
