package com.analitrix.sellbook.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private UserFilterableColumnsEnum filterBy;
    private String filter;
    private Boolean flatten;
    private int limit;
    private int offset;
}
