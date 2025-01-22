package com.analitrix.sellbook.dto;

import com.analitrix.sellbook.helpers.dto.RequestGetDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceRequestDto extends RequestGetDto {
    private String invoiceUser;
    private SortEnum sort=SortEnum.ASC;
    private InvoiceSortableColumnsEnum sortableColumn=InvoiceSortableColumnsEnum.expeditionDate;

}
