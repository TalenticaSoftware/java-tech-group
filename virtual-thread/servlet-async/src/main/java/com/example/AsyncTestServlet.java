package com.example;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(name = "AsyncTestServlet", urlPatterns = {"/test/async"}, asyncSupported = true)
public class AsyncTestServlet extends HttpServlet {
    private ExecutorService executorService = Executors.newFixedThreadPool(5); // Example: 5 threads

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Main Thread: " + Thread.currentThread().getName());
        usingStartAsync(req, resp);
        System.out.println("Response started");
    }

    private void usingStartAsync(HttpServletRequest req, HttpServletResponse resp) {
        AsyncContext asyncContext = req.startAsync(req, resp);
        executorService.submit(() -> {
            System.out.println("Async Thread: " + Thread.currentThread().getName());
            try {

                ServletResponse response = asyncContext.getResponse();
                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                Thread.sleep(5000); // Simulate some processing time
                out.print("Hello, World!");
                out.flush();
                asyncContext.complete();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            } finally {
                asyncContext.complete();
            }
        });


    }

    @Override
    public void destroy() {
        executorService.shutdown();
    }
}
