package com.analitrix.sellbook.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @Schema(defaultValue = "pablo.perez@example.com")
    private String mail;
    @Schema(defaultValue = "pass123456")
    private String password;
}
