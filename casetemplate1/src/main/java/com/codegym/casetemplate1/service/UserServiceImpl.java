package com.codegym.casetemplate1.service;

import com.codegym.casetemplate1.model.User;

import java.sql.*;
import java.util.function.Predicate;

public class UserServiceImpl implements IUserService{

    private static final String CHECK_USERNAME_PASSWORD = "SELECT * FROM `user` where email like ? and password = ?";
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
    public User checkUsernamePassword(String username, String password) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            System.out.println("checkUsernamePassword: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long idRs = rs.getLong("id");
                String nameRs = rs.getString("email");
                String passwordRs = rs.getString("password");
                int roleidRs = rs.getInt("roleid");
                User user = new User(idRs, nameRs, passwordRs, roleidRs);

                return user;
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return null;
    }
}
