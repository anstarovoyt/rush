package ru.naumen.servlet;

import org.springframework.web.servlet.HttpServletBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class DefaultServlet extends HttpServletBean {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.getWriter().append("result: ");
    }
}
