package com.mycomp.download;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downloadservlet")
public class DownLoadServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // 接收文件名参数
        String filename = request.getParameter("filename");
        System.out.println("filename: " + filename);

        // 根据文件名获取MIME类型
        String mimeType = this.getServletContext().getMimeType(filename);
        System.out.println("mimeType: " + mimeType);

        // 设置响应的MIME类型
        response.setContentType(mimeType + ";charset=UTF-8");

        // 告诉浏览器此文件链接是以附件的形式直接下载下来, 而不是解析并打开它
        // 如果文件名是中文的,需要对其进行编码
        // 而且要根据不同的浏览器进行不同的编码 (此处没写)
        response.setHeader("content-disposition",
                "attachment;filename=" + filename);

        // 拼接文件的绝对路径
        String realPath = this.getServletContext()
                .getRealPath("download/" + filename);

        // 加载文件, 读取, 并相应到页面
        FileInputStream in = new FileInputStream(realPath);
        ServletOutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
    }

}
