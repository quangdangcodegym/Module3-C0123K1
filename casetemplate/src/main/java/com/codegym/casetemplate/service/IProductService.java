package com.codegym.casetemplate.service;

import com.codegym.casetemplate.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();
    void addProduct(Product product);
    void updateProduct(long idProduct, Product product);

    void deleteProduct(long idProduct);
}
