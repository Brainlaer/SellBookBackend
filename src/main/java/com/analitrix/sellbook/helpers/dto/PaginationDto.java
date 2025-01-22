package com.analitrix.sellbook.helpers.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDto {
    private int limit=10;
    private int offset=0;
}
