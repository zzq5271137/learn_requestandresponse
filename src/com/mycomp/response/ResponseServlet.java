package com.mycomp.response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseservlet")
public class ResponseServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * =======================设置响应行=======================
         */
        /*
         *  设置状态码 (响应行)
         */
        response.setStatus(200);
        // response.setStatus(302);

        /**
         * =======================设置响应头=======================
         */
        /*
         *  添加响应头
         */
        response.addHeader("name", "zzq");
        response.addIntHeader("age", 20);
        response.addDateHeader("birthday", new Date().getTime());

        /*
         *  修改响应头
         */
        response.setHeader("name", "zzq2");
        response.setIntHeader("age", 27);

        /*
         * 设置重定向 
         * (先设置状态码为302, 上面已经做过) 
         * 
         * 重定向的路径为: /工程名的发布名/servlet的url-pattern名 
         * 注意点:
         * 1. 工程名的发布名前面有/
         * 2. 一定是工程的发布名,或叫path名(即访问时,在浏览器中地址栏中输入的)
         * 3. 一定是servlet的url-pattern名(即访问时,在浏览器中地址栏中输入的)
         * 
         * 原理是这样的:
         * 这里设置的location的值,在重定向时,其实会原封不动的拷贝到地址栏的域名后面,
         * 即"http://localhost:8080"的后面.
         */
        // response.setHeader("location", "/learnrequestandresponse/locationservlet");
        
        /*
         * 另一种重定向的方法.
         * 封装了上面的过程,就不用自己去设置header里的location.
         * 同样,传入的参数同上面一样
         */
        // response.sendRedirect("/learnrequestandresponse/locationservlet");
        
        /*
         * 定时刷新:
         * 过一段时间后,再重定向
         */
        // 过3秒之后重定向到后面的url
        // response.setHeader("refresh", "3;url=/learnrequestandresponse/locationservlet");
        
        /**
         * =======================设置响应体=======================
         */
        /*
         *  设置响应体两种方式
         */
        // 这里只是设置response缓冲区使用的编码
        // 如果想让中文内容在浏览器中正确的显示,还需要你的浏览器的编码也为UTF-8
        // response.setCharacterEncoding("UTF-8");
        
        // 或者你可以主动告诉浏览器使用UTF-8编码来接收,通过content-type来设置
        // response.setHeader("content-type", "text/html;charset=UTF-8");
        
        // 或者可以使用内部封装的方法来完成上面两步(setContentType方法)
        // 这里setContentType方法不仅设置了浏览器的编码,同时也自动设置了缓冲区的编码,使其保持一致
        response.setContentType("text/html;charset=UTF-8");
        
        // 方式一: 通过writer来写字符
        PrintWriter writer = response.getWriter();
        writer.write("hello");
        writer.write("<h1>hello</h1>");
        // 写中文,需要设置缓冲区中使用的编码(在上面)
        writer.write("<h1>挣命</h1>");
        
        // 方式二: 通过OutputStream来写文件
        // 这里只介绍读文件,读写文件的完整流程参考ResponseImgServlet.java
        // 方式一和方式二同时只能使用一个
        // 要先加载文件,一定要使用绝对路径
        String realPath = this.getServletContext().getRealPath("a.txt");
        FileInputStream in = new FileInputStream(realPath);
        
        // 获取加载的字节码
        // System.out.println(in.read()); // 读一个字符
        // System.out.println(in.read()); // 再读一个字符,如果此时文件读完没字符了就返回-1
        
        byte[] buffer = new byte[3];
        int len = 0;
        // 读取全部内容
        while ((len = in.read(buffer)) != -1) {
            System.out.println(new String(buffer, 0, len));
        }
        
        //关闭流
        in.close();
    }
}
