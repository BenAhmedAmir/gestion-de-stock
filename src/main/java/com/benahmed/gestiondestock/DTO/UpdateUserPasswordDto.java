package com.benahmed.gestiondestock.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateUserPasswordDto {
    private Integer id;
    private String password;
    private String confirmPassword;
}
