package com.codegym.helloservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "TranslateServlet", value = "/translate")
public class TranslateServlet extends HttpServlet {
    private Map<String, String> map;

    @Override
    public void init() throws ServletException {
        map = new HashMap<>();
        map.put("Hello", "Xin chào");
        map.put("Apple", "Quả táo");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // RequestDispatcher điều phối request
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txtSearch = req.getParameter("txtSearch");
        String result = map.get(txtSearch);

        req.setAttribute("result", result);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);
    }
}
