package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.CategoryDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;
    @Test
    public void shouldSaveCategoryWithSucces(){
        CategoryDto exceptedReslt =  CategoryDto.builder()
                .categoryCode("Cat test")
                .designation("designation test")
                .idEntreprise(1)
                .build();
        CategoryDto savedCategory = categoryService.save(exceptedReslt);
        Assertions.assertNotNull(savedCategory);
        Assertions.assertNotNull(savedCategory.getId());
        Assertions.assertEquals(exceptedReslt.getCategoryCode(),  savedCategory.getCategoryCode());
        Assertions.assertEquals(exceptedReslt.getDesignation(),  savedCategory.getDesignation());
        Assertions.assertEquals(exceptedReslt.getIdEntreprise(),  savedCategory.getDesignation());

    }
    @Test
    public void shouldUpdateCategoryWithSucces(){
        CategoryDto exceptedReslt =  CategoryDto.builder()
                .categoryCode("Cat test")
                .designation("designation test")
                .idEntreprise(1)
                .build();
        CategoryDto savedCategory = categoryService.save(exceptedReslt);
        CategoryDto categoryToUpdate = savedCategory;
        categoryToUpdate.setCategoryCode("cat upup");
        Assertions.assertNotNull(categoryToUpdate);
        Assertions.assertNotNull(categoryToUpdate.getId());
        Assertions.assertEquals(categoryToUpdate.getCategoryCode(),  savedCategory.getCategoryCode());
        Assertions.assertEquals(categoryToUpdate.getDesignation(),  savedCategory.getDesignation());
        Assertions.assertEquals(categoryToUpdate.getIdEntreprise(),  savedCategory.getIdEntreprise());
    }
    @Test
    public void shouldThrowInvalidEntityException() {
        CategoryDto exceptedReslt = CategoryDto.builder().build();
        InvalidEntityException exceptionException = assertThrows(InvalidEntityException.class, () -> categoryService.save(exceptedReslt));
        assertEquals(ErrorCodes.CATEGORY_NOT_VALID, exceptionException.getErrorCode());
        assertEquals(1, exceptionException.getErrors().size());
        assertEquals("Veuillez renseigner le code de categorie",  exceptionException.getErrors().get(0));
    }
    @Test
    public void shouldThrowEntityNotFoundException() {
        CategoryDto exceptedReslt = CategoryDto.builder().build();
        EntityNotFoundException exceptionException = assertThrows(EntityNotFoundException.class, () -> categoryService.findById(0));
        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND, exceptionException.getErrorCode());
        assertEquals("Aucun category est trouvé avec l'id = 0 dans la base", exceptionException.getMessage());
    }
    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowNotFoundEntity(){
        categoryService.findById(0);
    }

}