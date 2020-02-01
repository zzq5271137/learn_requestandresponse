package com.mycomp.requestcontext;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * request域对象(RequestContext)
 */

@WebServlet("/requestcontextservlet1")
public class RequestContextServlet1 extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // 什么是域: 能够存取数据
        // 什么是域对象: 能够存取数据的对象
        // 域对象都有这些方法: setAttribute(), getAttribute(), removeAttribute()
        // 域对象都有作用域
        // 之前的ServletContext的作用域是整个web应用,从init方法开始到web应用关闭结束
        // RequestContext的作用域是一次请求
        // (包含请求转发的中间过程, 但不包括重定向的中间过程, 因为重定向本质上是发送了多个请求)

        request.setAttribute("username", "zzq");
        request.getRequestDispatcher("/requestcontextservlet2")
                .forward(request, response);
    }

}
