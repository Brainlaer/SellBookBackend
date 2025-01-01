package com.analitrix.sellbook.dto;

import com.analitrix.sellbook.dto.book.BookDtoId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDtoCreate {

    private String person;
    private List<BookDtoId> bookDtoIdList;
}
