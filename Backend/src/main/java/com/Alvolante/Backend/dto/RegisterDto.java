package com.Alvolante.Backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterDto {
    private String rut;
    private String nameParam;
    private String email;
    private String phone;
    private Date birthdate;
    private String password;
    private String role;
}
