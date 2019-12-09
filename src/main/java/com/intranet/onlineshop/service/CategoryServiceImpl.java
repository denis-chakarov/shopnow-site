package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Category;
import com.intranet.onlineshop.domain.models.service.CategoryServiceModel;
import com.intranet.onlineshop.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the category service interface
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, Validator validator) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    /**
     * @see CategoryService#addCategory(CategoryServiceModel)
     */
    @Override
    public CategoryServiceModel addCategory(CategoryServiceModel categoryServiceModel) {
        if (!validator.validate(categoryServiceModel).isEmpty()) {
            throw new IllegalArgumentException("Invalid Category");
        }

        Category category = this.modelMapper.map(categoryServiceModel, Category.class);

        return this.modelMapper.map(this.categoryRepository.saveAndFlush(category), CategoryServiceModel.class);
    }

    /**
     * @see CategoryService#findAllCategories()
     */
    @Override
    public List<CategoryServiceModel> findAllCategories() {
        return this.categoryRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    /**
     * @see CategoryService#findCategoryById(String)
     */
    @Override
    public CategoryServiceModel findCategoryById(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        return this.modelMapper.map(category, CategoryServiceModel.class);
    }

    /**
     * @see CategoryService#editCategory(String, CategoryServiceModel)
     */
    @Override
    public CategoryServiceModel editCategory(String id, CategoryServiceModel categoryServiceModel) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        category.setName(categoryServiceModel.getName());

        return this.modelMapper.map(this.categoryRepository.saveAndFlush(category), CategoryServiceModel.class);
    }

    /**
     * @see CategoryService#deleteCategory(String)
     */
    @Override
    public CategoryServiceModel deleteCategory(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        this.categoryRepository.delete(category);

        return this.modelMapper.map(category, CategoryServiceModel.class);
    }
}
