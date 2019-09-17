package com.zzp.travel.controller.servlet;

import com.zzp.travel.entity.ResultInfo;
import com.zzp.travel.entity.User;
import com.zzp.travel.service.IUserService;
import com.zzp.travel.service.Impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    private IUserService ius = new UserServiceImpl();//dao层业务处理
    //使用封装的方式

    /**
     * 实现注册功能
     * @param req
     * @param resp
     * @return ResultInfo
     */
    public ResultInfo register(HttpServletRequest req,HttpServletResponse resp){
        ResultInfo resultInfo = new ResultInfo();//信息处理
        //获取所有数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        //验证码处理
        HttpSession session = req.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        session.removeAttribute("checkCode");//清除它避免其重复存在
        String check = req.getParameter("check");
        if (!check.equalsIgnoreCase(checkCode)){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            return resultInfo;
        }
        //将map里的数据映射到user中
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = ius.register(user);
        if (flag){
            //成功
            resultInfo.setFlag(true);
        }else {
            //失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名已存在");
        }
        return resultInfo;
    }

    /**
     * 激活用户
     * @param req
     * @param resp
     */
    public void activeUser(HttpServletRequest req,HttpServletResponse resp){
        String code = req.getParameter("code");
        if (code==null) return ;
        UserServiceImpl usd = new UserServiceImpl();
        boolean flag = usd.active(code);
        //发送信息
        String msg = null;
        if (flag){
            msg = "激活成功请<a style='color: green;font-size=24px;' href='login.html'>登录</a>";
        }else {
            msg = "激活失败，请联系管理员";
        }
        resp.setContentType("text/html;charset=utf-8");
        try {
            resp.getWriter().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     * @param req
     * @param resp
     * @return ResultInfo
     */
    public ResultInfo login(HttpServletRequest req,HttpServletResponse resp){
        ResultInfo ri = new ResultInfo();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        System.out.println(username+" "+password);
        //验证码处理
        HttpSession session = req.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        session.removeAttribute("checkCode");//清除它避免其重复存在
        String check = req.getParameter("check");
        //验证码不正确
        if (!check.equalsIgnoreCase(checkCode)) return new ResultInfo(false,"验证码错误");

         User user = ius.checkLogin(username,password);
        //判断用户是否存在
        if (user==null) return new ResultInfo(false,"用户名或密码错误");
        //判断用户状态值是否为Y
        if(!"Y".equals(user.getStatus())) return new ResultInfo(false,"用户未激活请激活");
        //密码正确
        ri.setFlag(true);
        //将用户名存入session中，便于后面的提取
        req.getSession().setAttribute("user",user);
        return ri;
    }

    /**
     * 网页header位置显示已登录的用户名
     * @param req
     * @param resp
     * @return null为游客，！null为用户名
     */
    public String showName(HttpServletRequest req,HttpServletResponse resp){
        User user;
        user = (User) req.getSession().getAttribute("user");
        return user==null ? "游客":user.getUsername();
    }

    /**
     * 退出方法
     * @param req
     * @param resp
     */
    public void exit(HttpServletRequest req,HttpServletResponse resp){
        req.getSession().invalidate();//销毁所有session
        try {
            resp.sendRedirect(req.getContextPath()+"/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
