package com.zzp.travel.controller.servlet;

import com.zzp.travel.entity.PageBean;
import com.zzp.travel.entity.Route;
import com.zzp.travel.entity.User;
import com.zzp.travel.service.Impl.FavoriteServiceImpl;
import com.zzp.travel.service.IFavoriteService;
import com.zzp.travel.service.IRouteService;
import com.zzp.travel.service.Impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@WebServlet("/RouteServlet")
public class RouteServlet extends BaseServlet{
    private IRouteService irs = new RouteServiceImpl();//路线处理
    private IFavoriteService ifs = new FavoriteServiceImpl();//收藏处理

    /**
     * 处理线路页数信息，并且响应到前端
     * @param req
     * @param resp
     * @return
     * @throws UnsupportedEncodingException
     */
    public PageBean<Route>  pageQuery(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        // 接收前端请求
        String _currentPage = req.getParameter("currentPage");
        String _rows = req.getParameter("rows");
        String _cid = req.getParameter("cid");
        String rname = req.getParameter("rname");
        if ("null".equals(rname)) rname="";//防止前端传个 "null"
//        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        //对第一次传入的值进行处理
        int cid = 5;
        if (_cid!=null && _cid.length()>0 && !"null".equals(_cid)) cid = Integer.parseInt(_cid);
        int currentPage = 1;
        if (_currentPage!=null && _currentPage.length()>0) currentPage = Integer.parseInt(_currentPage);
        int rows = 5;
        if (_rows!=null && _rows.length()>0) rows = Integer.parseInt(_rows);
//        System.out.println(cid+" "+currentPage+" "+rows+" "+rname+" ");
        PageBean<Route> rpb =  irs.pageQuery(cid, currentPage, rows,rname);
        return rpb;
    }

    /**
     * 线路详细页的的信息处理，并响应
     * @param req
     * @param resp
     * @return
     */
    public Route detailQuery(HttpServletRequest req, HttpServletResponse resp){
        String _rid = req.getParameter("rid");
        int rid=5;
        if (_rid!=null && _rid.length()>0 && !"null".equals(_rid))  rid = Integer.parseInt(_rid);
        return irs.detailQuery(rid);
    }

    /**
     * 判断是否已经收藏
     * @param req
     * @param resp
     * @return
     */
    public boolean isFavorite(HttpServletRequest req, HttpServletResponse resp){
        String _rid = req.getParameter("rid");
        User user = (User)req.getSession().getAttribute("user");
        int rid =0;
        if (_rid!=null && !"null".equals(_rid) && !"".equals(_rid))  rid=Integer.parseInt(_rid);//非空判断
        int uid ;//用户id
        if (user==null) uid =0;//为登陆
        else uid = user.getUid();//已经登陆
        return ifs.isFavoriteService(rid,uid);
    }

    public void addFavorite(HttpServletRequest req, HttpServletResponse resp){
        String rid = req.getParameter("rid");
        User user = (User)req.getSession().getAttribute("user");
        int uid = 0;
        if (user==null) return;
        uid = user.getUid();
        ifs.addFavorite(rid,uid);
    }

    public void cancelFavorite(HttpServletRequest req, HttpServletResponse resp){
        String rid = req.getParameter("rid");
        User user = (User)req.getSession().getAttribute("user");
        int uid = 0;
        if (user==null) return;//未登录
        uid = user.getUid();
        ifs.cancelFavority(rid,uid);
    }

    /**
     * 查看我的收藏
     * @param req
     * @param resp
     * @return
     */
    public PageBean<Route> allFavorites(HttpServletRequest req, HttpServletResponse resp){
        String currentPageStr = req.getParameter("currentPage");
//        String rowsStr = req.getParameter("rows");
        User user = (User)req.getSession().getAttribute("user");
        int uid = user.getUid();
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int row =8;//每页显示条数，如果不传递，默认每页显示8条记录
        return ifs.queryFavorite(uid, currentPage, row);
    }
}
