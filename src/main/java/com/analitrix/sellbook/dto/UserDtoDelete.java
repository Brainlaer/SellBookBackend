package com.analitrix.sellbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoDelete {

    private Long id;
    private String mail;
    private String password;
}
