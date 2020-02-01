package com.mycomp.request;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/*
 * 使用BeanUtils把所有的参数封装成对象
 */

@WebServlet("/beanutilsservlet")
public class BeanUtilsServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        // 需要先获取ParameterMap
        Map<String, String[]> parameterMap = request.getParameterMap();

        // 用之前的方法获取所有参数
        System.out.println("用之前的方法获取所有参数: ");
        for (Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println(entry.getKey() + " = "
                    + Arrays.toString(entry.getValue()));
        }

        // 把获取的请求参数封装成对象(使用BeanUtils)
        // 为什么要这么做,因为我们可以直接把对象存到数据库当中(使用DBUtils)
        System.out.println("用BeanUtils获取所有参数并直接封装成对象: ");
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }

}
