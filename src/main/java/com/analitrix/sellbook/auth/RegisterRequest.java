package com.analitrix.sellbook.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Long id;
    private String name;
    private String surname;
    private Long phone;
    private String mail;
    private String password;
    private String homeAddress;


}
