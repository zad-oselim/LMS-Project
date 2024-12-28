package com.spring.model.dto;

import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int userId;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String role;
}