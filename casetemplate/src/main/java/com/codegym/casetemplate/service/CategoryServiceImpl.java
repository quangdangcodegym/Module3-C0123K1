package com.codegym.casetemplate.service;

import com.codegym.casetemplate.model.Category;
import com.codegym.casetemplate.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService{
    private static final String FIND_ALL_CATEGORIES = "SELECT * FROM category;";
    private static final String FIND_CATEGORY_BY_ID = "SELECT * FROM category where id = ?";
    private String jdbcURL = "jdbc:mysql://localhost:3306/c0123_product_manager?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Raisingthebar123!!/";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public List<Category> findAllCategory() {
        List<Category> categories = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CATEGORIES);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long idCategory = rs.getLong("id");
                String nameCategory = rs.getString("name");

                Category c = new Category(idCategory, nameCategory);
                categories.add(c);
            }

            System.out.println("findAllProducts: "  + preparedStatement);

            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return categories;
    }

    @Override
    public Category findCategoryById(long idCategory) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CATEGORY_BY_ID);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String nameCategory = rs.getString("name");

                Category c = new Category(id, nameCategory);
                return c;
            }

            System.out.println("findCategoryById: "  + preparedStatement);

            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
