package com.bronya.mvnTomcat.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/attribute")
public class AttributeTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext context = getServletContext();
        context.setAttribute("username", "root"); // insert
        context.setAttribute("password", "0000"); // insert
        context.setAttribute("password", "1024"); // update
        String username = (String) context.getAttribute("username"); // select
        System.out.println(username);
        context.removeAttribute("password");
    }
}