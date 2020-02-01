package com.mycomp.request;

import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestpostservlet")
public class RequestPostServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
        // post请求中如果有中文参数会有编码问题
        // String username = request.getParameter("username");
        // System.out.println(username);
        // 可以采用重新编码的方式
        // byte[] bytes = username.getBytes("ISO8859-1");
        // String usernameStr = new String(bytes, "UTF-8");
        // System.out.println(usernameStr);

        // 但是如果每一个参数都重新编码一遍会很麻烦
        // 而且有时候也不知道传过来的参数是什么编码, 就无法重新编码
        // 可以直接设置请求时使用的编码(使用setCharacterEncoding()方法)
        // 但是这种方法只适用于post, 对get请求不能用
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        System.out.println(username);
    }
}
