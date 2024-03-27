package com.coqueiro.demoparkapi.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPasswordDto {

    private String actualPassword;
    private String newPassword;
    private String passwordConfirmation;
}
