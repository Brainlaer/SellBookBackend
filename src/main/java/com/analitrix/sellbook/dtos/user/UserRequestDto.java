package com.analitrix.sellbook.dtos.user;

import com.analitrix.sellbook.enums.SortEnum;
import com.analitrix.sellbook.enums.UserSortableColumnsEnum;
import com.analitrix.sellbook.dtos.PaginationDto;
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
