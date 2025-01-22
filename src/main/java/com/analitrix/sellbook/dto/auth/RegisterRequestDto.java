package com.analitrix.sellbook.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @Schema(defaultValue = "Cedula")
    private String documentType;
    @Schema(defaultValue = "123456789")
    private Long documentNumber;
    @Schema(defaultValue = "Pablo")
    private String name;
    @Schema(defaultValue = "Perez")
    private String surname;
    @Schema(defaultValue = "321456789")
    private Long phone;
    @Schema(defaultValue = "pablo.perez@example.com")
    private String mail;
    @Schema(defaultValue = "pass123456")
    private String password;
    @Schema(defaultValue = "Cra.50 #50-50")
    private String homeAddress;
}
