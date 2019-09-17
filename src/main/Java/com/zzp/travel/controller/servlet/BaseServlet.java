package com.zzp.travel.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取action
        String action = req.getParameter("action");
        //2.获取当前页面的反射对象
        Class<? extends BaseServlet> aClass = this.getClass();
        //获取action对应的方法
        try {
            //获取是使用的方法对象
            Method method = aClass.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //取消权限
            method.setAccessible(true);
            //使用方法并且获取返回值
            Object msg = method.invoke(this, req, resp);
            if (msg==null) return;//如果msg为null则不发生json数据
            //由放回值发送json
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(msg);
            //响应前端
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
