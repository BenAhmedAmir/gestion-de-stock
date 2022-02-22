package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.CategoryDto;
import com.benahmed.gestiondestock.DTO.ClientDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.Category;
import com.benahmed.gestiondestock.repository.CategoryRepository;
import com.benahmed.gestiondestock.service.CategoryService;
import com.benahmed.gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error(" la categorie nest pas valide {} ", dto);
            throw new InvalidEntityException("la categorie nest pas valide " , ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.fromEntity(
                categoryRepository.save(CategoryDto.toEntity(dto))
        );
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null){
            log.error("l'id est null");
            return null;
        }
        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun category est trouvé avec l'id = " + id + "dans la base",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ));
    }


    @Override
    public CategoryDto findByCategoryCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("le catégorie code est null");
            return null;
        }
        return categoryRepository.findByCategoryCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun category est trouvé avec le code = " + code + "dans la base",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ));
    }


    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id ==null){
            log.error("l'id est null");
            return;
        }
        categoryRepository.deleteById(id);
    }
}
