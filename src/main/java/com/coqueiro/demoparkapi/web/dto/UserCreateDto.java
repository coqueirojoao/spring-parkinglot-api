package com.coqueiro.demoparkapi.web.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserCreateDto {

    private String username;
    private String password;
}
