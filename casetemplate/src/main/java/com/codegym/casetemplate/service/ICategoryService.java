package com.codegym.casetemplate.service;


import com.codegym.casetemplate.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategory();

    Category findCategoryById(long idCategory);

    /**
    void addCategory(Category category);
    void updateCategory(long idCategory, Category category);
    void deleteCategory(long idCategory);
     **/
}
