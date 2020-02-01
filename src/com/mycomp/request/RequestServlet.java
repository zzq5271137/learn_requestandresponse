package com.mycomp.request;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestservlet")
public class RequestServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response) {
        /**
         * =======================获得请求行=======================
         */
        // 获取请求方式(get, post, put...)
        String method = request.getMethod();
        System.out.println("getMethod(): " + method);

        // 获取请求资源(URL和URI)
        StringBuffer url = request.getRequestURL();
        System.out.println("getRequestURL(): " + url);
        String uri = request.getRequestURI();
        System.out.println("getRequestURI(): " + uri);

        // 获取get请求参数(?之后的字符串)
        String queryString = request.getQueryString();
        System.out.println("getQueryString(): " + queryString);

        // 获取当前web应用的名称(记得在server.xml里有context标签用来配置每个web应用)
        String contextPath = request.getContextPath();
        System.out.println("getContextPath(): " + contextPath);

        /**
         * =======================获得请求头=======================
         */
        // 获取所有的请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println("所有的请求头名称:");
        while (headerNames.hasMoreElements()) {
            System.out.println(headerNames.nextElement());
        }

        // 根据请求头名称获取指定的头信息
        // referer可以用于防盗链
        // 通过以下形式发送请求才会有referer:
        // 1. 通过<a href="">
        // 2. 通过表单提交的时候
        // 3. 通过JS提交表单的时候
        // 以下方式不会有referer请求头
        // 1. 从收藏夹链接
        // 2. 单击主页或自定义地址
        // 3. 从浏览器地址栏直接输入地址
        String referer = request.getHeader("referer");
        System.out.println("referer: " + referer);

        // 打印所有的请求头和请求内容
        System.out.println("所有的请求头和请求内容:");
        Enumeration<String> headerNames2 = request.getHeaderNames();
        while (headerNames2.hasMoreElements()) {
            String headerName = headerNames2.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ": " + headerValue);
        }

        /**
         * =======================获得请求体=======================
         */
        // 获取一个值
        System.out.println("获取单个请求参数:");
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 获取多个值 (<input type="checkbox">多选)
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("获取多选值, 爱好为: " + Arrays.toString(hobbies));

        // 获取所有请求参数名称
        System.out.println("所有的请求参数名: ");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            System.out
                    .println("paramter name: " + parameterNames.nextElement());
        }

        // 获取所有请求参数
        System.out.println("获取所有的请求参数: ");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println(
                    entry.getKey() + "=" + Arrays.toString(entry.getValue()));
        }
    }

}
