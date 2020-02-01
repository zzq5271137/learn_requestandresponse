package com.mycomp.requestcontext;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestcontextservlet2")
public class RequestContextServlet2 extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("requestcontextservlet2 service...");

        String username = (String) request.getAttribute("username");
        System.out.println(username);
    }

}
