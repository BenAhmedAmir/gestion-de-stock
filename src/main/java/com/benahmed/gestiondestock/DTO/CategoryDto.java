package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class CategoryDto {
    private Integer id;

    private String categoryCode;

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
                .categoryCode(category.getCategoryCode())
                .designation(category.getDesignation())
                .articles(category.getArticles() != null ?
                        category.getArticles().stream()
                                .map(ArticleDto::fromEntity)
                                .collect(Collectors.toList()) : null)
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
        category.setCategoryCode(categoryDto.getCategoryCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setArticles(categoryDto.getArticles() != null ?
                categoryDto.getArticles().stream()
                .map(ArticleDto::toEntity)
                        .collect(Collectors.toList()) : null);

        return category;
    }
}
