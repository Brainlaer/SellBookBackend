package com.analitrix.sellbook.dtos.invoice;

import com.analitrix.sellbook.enums.SortEnum;
import com.analitrix.sellbook.dtos.PaginationDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceRequestDto extends PaginationDto {
    private String invoiceUser;
    private SortEnum sort=SortEnum.ASC;
    private InvoiceSortableColumnsEnum sortableColumn=InvoiceSortableColumnsEnum.expeditionDate;

}
