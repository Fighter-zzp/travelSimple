package com.zzp.travel.controller.servlet;

import com.zzp.travel.entity.Category;
import com.zzp.travel.service.ICategoryService;
import com.zzp.travel.service.Impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    private ICategoryService ics = new CategoryServiceImpl();
    /**
     * 导航栏信息处理
     * @param request
     * @param response
     * @return
     */
    public List<Category> navAll(HttpServletRequest request, HttpServletResponse response){
        //查询说要信息，传到前端中
        return ics.findAll();
    }

}
