package com.analitrix.sellbook.dto.user;

import com.analitrix.sellbook.helpers.dto.RequestGetDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto extends RequestGetDto {
    private UserFilterableColumnsEnum filterBy;
}
