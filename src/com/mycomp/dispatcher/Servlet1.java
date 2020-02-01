package com.mycomp.dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 请求转发
 */

@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // 请求转发和重定向的区别:
        // 重定向是指,浏览器向一个servlet发送请求,servlet告诉浏览器去访问另一个servlet
        // 这里,浏览器实际发送了两个请求,而且地址栏会发生变化
        // 而请求转发是指,浏览器向一个servlet发送请求,servlet直接转发到另一个servlet
        // 这里,浏览器实际只发送了一个请求,而且地址栏没有变化
        // 重定向是通过response设置的(参考response包中的ResponseServlet)
        // 请求转发是通过request设置的

        // 请求转发分为两步:
        // 1. 获取request中的转发器
        // 转发器中传入的参数是要转发的对象地址,和重定向不同,这里不需要写工程名称
        // 因为请求转发是在服务器内部完成的,它使用的是服务器地址
        // (重定向使用的是客户端地址)
        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher("/servlet2");
        // 2. 转发器调用forward方法进行转发
        requestDispatcher.forward(request, response);
    }

}
