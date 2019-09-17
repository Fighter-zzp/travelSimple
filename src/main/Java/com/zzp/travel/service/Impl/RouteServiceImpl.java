package com.zzp.travel.service.Impl;

import com.zzp.travel.Dao.IFavoriteDao;
import com.zzp.travel.Dao.IRouteDao;
import com.zzp.travel.Dao.IRouteImgDao;
import com.zzp.travel.Dao.ISellerDao;
import com.zzp.travel.Dao.impl.FavorityDaoImpl;
import com.zzp.travel.Dao.impl.RouteDaoImpl;
import com.zzp.travel.Dao.impl.RouteImgDaoImpl;
import com.zzp.travel.Dao.impl.SellerDaoImpl;
import com.zzp.travel.entity.PageBean;
import com.zzp.travel.entity.Route;
import com.zzp.travel.entity.RouteImg;
import com.zzp.travel.entity.Seller;
import com.zzp.travel.service.IRouteService;

import java.util.List;

public class RouteServiceImpl implements IRouteService {
    private IRouteDao ird = new RouteDaoImpl();//处理线路数据表
    private IRouteImgDao iri = new RouteImgDaoImpl();//处理图像数据表
    private ISellerDao isd = new SellerDaoImpl();//处理运营商表
    private IFavoriteDao ifd = new FavorityDaoImpl();//处理收藏表


    /**
     * 获取分页或的数据
     * @param cid
     * @param currentPage
     * @param rows
     * @param rname
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int rows, String rname) {
        PageBean<Route> rpb = new PageBean<>();
        //存入可直接获取的
        Integer totalRows = ird.findTotalCountByCid(cid,rname);
        rpb.setTotalRows(totalRows);
        rpb.setCurrentPage(currentPage);
        rpb.setRows(rows);
        //操作间接数据
        int totalPage = totalRows/rows==0?totalRows/rows:totalRows/rows+1;
        rpb.setTotalPage(totalPage);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * rows;//开始的记录数
        List<Route> list = ird.queryList(cid, rname,start, rows);
        rpb.setList(list);
        return rpb;
    }

    /**
     * 通过rid得到完整的route数据
     * @param rid
     * @return
     */
    @Override
    public Route detailQuery(int rid) {
        //1.有rid获取route对象
        Route route = ird.queryOne(rid);
        //2.由rid获取routeImg对象
        List<RouteImg> list = iri.findAll(route.getRid());
        route.setRouteImgList(list);
        //3.有route对象获取sid后，获取seller对象
        Seller seller = isd.findOne(route.getSid());
        route.setSeller(seller);
        //4.获取收藏表中的个数
        int count = ifd.findCount(rid);
        route.setCount(count);
        return route;
    }
}
