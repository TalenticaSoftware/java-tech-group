package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestServlet", urlPatterns = {"/test"}, asyncSupported = false)
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Current Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            resp.setContentType("text/plain");
            resp.getWriter().write("Hello, World!");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
