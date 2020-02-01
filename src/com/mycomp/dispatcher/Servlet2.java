package com.mycomp.dispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet2")
public class Servlet2 extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("servlet2 service...");
        // response.setContentType("txt/html;charset=UTF-8");
        // response.getWriter().write("<h1>欢迎来到servlet2</h1>");
    }

}
