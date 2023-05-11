package com.codegym.casetemplate1;

import com.codegym.casetemplate1.config.AppUtils;
import com.codegym.casetemplate1.model.User;
import com.codegym.casetemplate1.service.IUserService;
import com.codegym.casetemplate1.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private IUserService iUserService;

    @Override
    public void init() throws ServletException {
        iUserService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Login" + req.getSession().getId());


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppUtils.VIEW_FRONTEND_PATH + "login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = iUserService.checkUsernamePassword(username, password);

        if (user!=null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/products");
        }else {
            req.setAttribute("message", "Thông tin đăng nhập không hợp lệ");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppUtils.VIEW_FRONTEND_PATH + "login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
