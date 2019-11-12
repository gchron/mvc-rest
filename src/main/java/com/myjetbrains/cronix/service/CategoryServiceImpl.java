package com.myjetbrains.cronix.service;

import com.myjetbrains.cronix.api.v1.mapper.CategoryMapper;
import com.myjetbrains.cronix.api.v1.model.CategoryDTO;
import com.myjetbrains.cronix.domain.Category;
import com.myjetbrains.cronix.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOS =
                categoryRepository.findAll().stream()
                        .map(category -> categoryMapper.categoryToCategoryDTO(category))
                        .collect(Collectors.toList());
        return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
