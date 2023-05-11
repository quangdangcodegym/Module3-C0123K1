package com.codegym.casetemplate1.service;

import com.codegym.casetemplate1.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();

    List<Product> findAllProductsPagging(String kw, long idCategory, int offset, int limit);

    int getNoOfRecords();
    void addProduct(Product product);
    void updateProduct(long idProduct, Product product);

    void deleteProduct(long idProduct);
}
