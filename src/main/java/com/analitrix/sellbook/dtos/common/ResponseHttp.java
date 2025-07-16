package com.analitrix.sellbook.dtos.common;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHttp {
    private int code;
    private Object detail;
}
