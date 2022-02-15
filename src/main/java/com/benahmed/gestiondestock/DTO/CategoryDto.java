package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {
    private Integer id;

    private String code;

    private String designation;
    private List<ArticleDto> articles;
    public static CategoryDto fromEntity(Category category){
        if (category == null){
            return null;
            // TODO throw an exception
        }
        // mapping Category vers CategoryDTO
        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                //la methode build va construire un obj de type categoryDTO
                .build();
    }
    public static Category toEntity(CategoryDto categoryDto){
        if (categoryDto == null){
            return null;
            // TODO throw an exception
        }
        // mapping CategoryDTO vers Category
        // instance
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        return category;
    }
}
