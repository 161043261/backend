package com.bronya.servlet.servlets;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/path")
public class Path extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = getServletContext(); // this.getServletContext();

        String webapp = servletContext.getRealPath("./");
        System.out.println(webapp);
        // cd ./src/webapp
        // servletContext.getRealPath("./") == pwd

        String statik = servletContext.getRealPath("./static");
        System.out.println(statik);
        // cd ./src/webapp/static
        // servletContext.getRealPath("./static") == pwd

        String webInformation = servletContext.getRealPath("./WEB-INF");
        System.out.println(webInformation);
        // cd ./src/webapp/WEB-INF
        // servletContext.getRealPath("./WEB-INF") == pwd

        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath); // /servlet
    }
}