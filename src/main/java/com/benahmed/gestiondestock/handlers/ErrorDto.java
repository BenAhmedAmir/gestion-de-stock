package com.benahmed.gestiondestock.handlers;

import com.benahmed.gestiondestock.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode;
    private ErrorCodes code;
    private String msg;
    private List<String> errors = new ArrayList<>();
}
