package com.analitrix.sellbook.helpers.dto;

import com.analitrix.sellbook.dto.user.UserFilterableColumnsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestGetDto {
    private int limit=10;
    private int offset=0;
    private boolean flatten;
}
