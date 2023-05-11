package com.codegym.casetemplate1.service;


import com.codegym.casetemplate1.model.Category;

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
