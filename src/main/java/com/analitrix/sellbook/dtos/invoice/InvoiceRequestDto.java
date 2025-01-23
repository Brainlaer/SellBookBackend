package com.analitrix.sellbook.dtos.invoice;

import com.analitrix.sellbook.enums.SortEnum;
import com.analitrix.sellbook.dtos.common.PaginationDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceRequestDto extends PaginationDto {
    private String user;
    private SortEnum sort=SortEnum.ASC;
    private InvoiceSortableColumnsEnum sortableColumn=InvoiceSortableColumnsEnum.expeditionDate;

}
