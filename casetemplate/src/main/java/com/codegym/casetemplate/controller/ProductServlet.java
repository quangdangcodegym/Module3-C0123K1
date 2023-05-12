package com.codegym.casetemplate.controller;



import com.codegym.casetemplate.model.Category;
import com.codegym.casetemplate.model.Product;
import com.codegym.casetemplate.service.CategoryServiceImpl;
import com.codegym.casetemplate.service.ICategoryService;
import com.codegym.casetemplate.service.IProductService;
import com.codegym.casetemplate.service.ProductServiceImpl;
import com.codegym.casetemplate.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
        System.out.println(productService.getTotalPriceProduct(1));

        String kw = "";
        if (req.getParameter("kw") != null) {
            kw = req.getParameter("kw");
        }
        long idCategory = -1;
        if (req.getParameter("idcategory") != null) {
            idCategory = Long.parseLong(req.getParameter("idcategory"));
        }
        int page = 1;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int limit = 3;
        if (req.getParameter("limit") != null) {
            limit = Integer.parseInt(req.getParameter("limit"));
        }



        List<Product> products = productService.findAllProductsPagging(kw, idCategory, (page - 1) * limit, limit);
        List<Category> categories = categoryService.findAllCategory();

        // Lấy tổng so trang
        int noOfRecords = productService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / limit);     // hàm ceil làm tròn lên: 10/3 = 3,33 => làm tròn 4

        req.setAttribute("products", products);
        req.setAttribute("categories", categories);

        req.setAttribute("kw", kw);
        req.setAttribute("idcategory", idCategory);
        req.setAttribute("currentPage", page);
        req.setAttribute("limit", limit);
        req.setAttribute("noOfPages", noOfPages);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                createProduct(req, resp);
                break;
            case "edit":
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long idProduct = Long.parseLong(req.getParameter("id"));

        productService.deleteProduct(idProduct);

        resp.sendRedirect("/products");
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Product product = new Product();

        validateName(req, errors, product);
        validateDescription(req, errors, product);
        validatePrice(req, errors, product);
        validateDate(req, errors, product);
        validateCategory(req, errors, product);


        if (errors.isEmpty()) {
            productService.addProduct(product);
            req.setAttribute("message", "Add product success");
        }else{
            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
            List<Category> categories = categoryService.findAllCategory();
            req.setAttribute("categories", categories);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/product/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void validateCategory(HttpServletRequest req, List<String> errors, Product product) {
        try {
            long categoryId = Long.parseLong(req.getParameter("category"));
            Category c = categoryService.findCategoryById(categoryId);
            if (c == null) {
                errors.add("Danh mục điện thoại không hợp lệ");
            }else{
                product.setIdCategory(categoryId);
            }

        } catch (NumberFormatException numberFormatException) {
            errors.add("Danh mục điện thoại không hợp lệ");
        }
    }
    private void validateDate(HttpServletRequest req, List<String> errors, Product product) {
        try {
            Date createAt = Date.valueOf(req.getParameter("create_at"));
            product.setCreateAt(createAt);

        } catch (IllegalArgumentException illegalArgumentException) {
            errors.add("Ngày không hợp lệ");
        }
    }
    private void validatePrice(HttpServletRequest req, List<String> errors, Product product) {
        try {
            float price = Float.parseFloat(req.getParameter("price"));
            if (price < 0 || price > 10000000) {
                errors.add("Giá phải lớn hơn 0 và nhỏ hơn 10000000");
            }else{
                product.setPrice(price);
            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("Định dạng giá không hop lệ");
        }


    }
    private void validateDescription(HttpServletRequest req, List<String> errors, Product product) {
        String description = req.getParameter("description");
        if (!ValidateUtils.isDescriptionValid(description)) {
            errors.add("Tên không hợp lệ. Phải bắt đầu là chữ số và có từ 15-50 kí tự!");
        }
        product.setDescription(description);
    }
    private void validateName(HttpServletRequest req, List<String> errors, Product product) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isNameValid(name)) {
            errors.add("Tên không hợp lệ. Phải bắt đầu là chữ số và có từ 8-20 kí tự!");
        }
        product.setName(name);
    }
}
