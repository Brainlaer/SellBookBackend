package com.analitrix.sellbook.dto;

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

    private Long person;
    private List<BookDtoId> bookDtoIdList;
}
