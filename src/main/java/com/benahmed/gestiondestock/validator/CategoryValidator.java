package com.benahmed.gestiondestock.validator;

import com.benahmed.gestiondestock.DTO.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {
    public static List<String> validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();
        if (categoryDto == null || StringUtils.hasLength(categoryDto.getCategoryCode())) {
            errors.add("Veuillez renseigner le code de la catégorie");
        }
        return errors;
    }
}
