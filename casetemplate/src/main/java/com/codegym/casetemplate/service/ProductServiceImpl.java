package com.codegym.casetemplate.service;

import com.codegym.casetemplate.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService{
    private static final String FIND_ALL_PRODUCTS = "SELECT * FROM product;";
    private static final String ADD_PRODUCT = "INSERT INTO `product` (`name`, `description`, `price`, `create_at`, `idcategory`) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_PRODUCT = "UPDATE `product` SET `name` = ?, `description` = ?, `price` = ?, `create_at` = ?, `idcategory` = ? WHERE (`id` = ?);";
    private static final String DELETE_PRODUCT = "DELETE FROM `product` WHERE (`id` = ?);";
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
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PRODUCTS);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long idProduct = rs.getLong("id");
                String nameProduct = rs.getString("name");
                String descriptionProduct = rs.getString("description");
                float priceProduct = rs.getFloat("price");
                Date createAt = rs.getDate("create_at");
                int idCategory = rs.getInt("idcategory");

                //long id, String name, String description,float price, Date createAt, int idCategory
                Product p = new Product(idProduct, nameProduct, descriptionProduct, priceProduct, createAt, idCategory);
                products.add(p);
            }

            System.out.println("findAllProducts: "  + preparedStatement);

            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return products;
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
    @Override
    public void addProduct(Product product) {
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setDate(4,  new java.sql.Date(product.getCreateAt().getTime()));
            preparedStatement.setLong(5, product.getIdCategory());

            System.out.println("addProduct: " + preparedStatement);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public void updateProduct(long idProduct, Product product) {
        try {
            Connection connection = getConnection();
            //UPDATE `product` SET `name` = ?, `description` = ?, `price` = ?, `create_at` = ?, `idcategory` = ? WHERE (`id` = ?);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setDate(4, new java.sql.Date(product.getCreateAt().getTime()));
            preparedStatement.setLong(5, product.getIdCategory());
            preparedStatement.setLong(6, product.getIdCategory());

            preparedStatement.executeUpdate();
            System.out.println("updateProduct: " + preparedStatement);

            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public void deleteProduct(long idProduct) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setLong(1, idProduct);

            preparedStatement.executeUpdate();

            System.out.println("deleteProduct: " + preparedStatement);

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
}
