package com.mycomp.response;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseimgservlet")
public class ResponseImgServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        /*
         * 向浏览器写一张图片
         */
        String realPath = this.getServletContext()
                .getRealPath("imgs/pic2.JPG");
        FileInputStream in = new FileInputStream(realPath);
        ServletOutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
    }

}
