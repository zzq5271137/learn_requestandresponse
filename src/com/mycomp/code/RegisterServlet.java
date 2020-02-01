package com.mycomp.code;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerservlet")
public class RegisterServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // 接收请求参数
        String inputCode = request.getParameter("code");
        System.out.println(inputCode);

        // 取出CheckCodeServlet在ServletContext中存放的word
        String checkCode = (String) this.getServletContext()
                .getAttribute("checkCode");

        // 设置content-type
        response.setContentType("text/html;charset=UTF-8");

        // 验证
        if (inputCode.equals(checkCode)) {
            response.getWriter().write("<h1>注册成功！</h1>");
        } else {
            response.getWriter().write("<h1>验证码错误...</h1>");
            // 3秒钟后跳转回注册页面
            response.setHeader("refresh",
                    "3;/learnrequestandresponse/code.html");
        }
    }

}
