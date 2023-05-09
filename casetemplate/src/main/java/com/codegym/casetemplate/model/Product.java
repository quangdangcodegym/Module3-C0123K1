package com.codegym.casetemplate.model;

import java.util.Date;

public class Product {
    private long id;
    private String name;
    private String description;

    private float price;
    private Date createAt;


    private long idCategory;

    public Product(long id, String name, String description,float price, Date createAt, long idCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createAt = createAt;
        this.idCategory = idCategory;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }
}
