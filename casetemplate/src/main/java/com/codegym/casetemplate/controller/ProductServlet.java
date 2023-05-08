package com.codegym.casetemplate.controller;



import com.codegym.casetemplate.model.Category;
import com.codegym.casetemplate.model.Product;
import com.codegym.casetemplate.service.CategoryServiceImpl;
import com.codegym.casetemplate.service.ICategoryService;
import com.codegym.casetemplate.service.IProductService;
import com.codegym.casetemplate.service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private IProductService productService;
    private ICategoryService categoryService;
    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
        categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                showAddProduct(req, resp);
                break;
            case "edit":
                break;
            default:
                showProducts(req, resp);
        }
    }

    private void showAddProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.findAllCategory();

        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/product/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAllProducts();
        List<Category> categories = categoryService.findAllCategory();

        req.setAttribute("products", products);
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
