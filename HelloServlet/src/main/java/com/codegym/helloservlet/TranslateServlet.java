package com.codegym.helloservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "TranslateServlet", value = "/translate")
public class TranslateServlet extends HttpServlet {
    private Map<String, String> map;

    @Override
    public void init() throws ServletException {
        map = new HashMap<>();
        map.put("Hello", "Xin chào");
        map.put("Love", "Yêu");
        map.put("Class", "Lớp học");
        map.put("Student", "Học sinh");
        map.put("Apple", "Quả táo");
        map.put("Air", "Không khí");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // RequestDispatcher điều phối request
        String vocabulary = req.getParameter("vocabolary");     // Hello

        String result = map.get(vocabulary);

        req.setAttribute("vocabulary", vocabulary);
        req.setAttribute("result", result);

        Set<String> vocabularies = map.keySet();    // [Hello, Apple, Air]
        for (String item : vocabularies) {
            if (item.equals(vocabulary)) {
                vocabularies.remove(item);
                break;
            }
        }
        req.setAttribute("vocabularies", vocabularies);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txtSearch = req.getParameter("txtSearch");
        String result = map.get(txtSearch);
        Set<String> vocabularies = map.keySet();

        req.setAttribute("result", result);
        req.setAttribute("vocabularies", vocabularies);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);




    }
}
