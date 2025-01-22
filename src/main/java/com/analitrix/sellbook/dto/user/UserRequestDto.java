package com.analitrix.sellbook.dto.user;

import com.analitrix.sellbook.dto.SortEnum;
import com.analitrix.sellbook.helpers.dto.PaginationDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto extends PaginationDto {
    private String documentNumber;
    private String name;
    private String username;
    private SortEnum sort= SortEnum.ASC;
    private UserSortableColumnsEnum sortableColumn=UserSortableColumnsEnum.name;
}
