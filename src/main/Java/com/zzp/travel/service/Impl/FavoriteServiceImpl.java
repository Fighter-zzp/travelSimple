package com.zzp.travel.service.Impl;

import com.zzp.travel.Dao.IFavoriteDao;
import com.zzp.travel.Dao.impl.FavorityDaoImpl;
import com.zzp.travel.entity.PageBean;
import com.zzp.travel.entity.Route;
import com.zzp.travel.entity.User;
import com.zzp.travel.service.IFavoriteService;

import java.util.List;

public class FavoriteServiceImpl implements IFavoriteService {
    private IFavoriteDao ifd = new FavorityDaoImpl();
    @Override
    public boolean isFavoriteService(int rid, int uid) {
        return ifd.findFavorite(rid,uid)!=null;
    }

    @Override
    public void addFavorite(String rid, int uid) {
        ifd.addFavorite(Integer.parseInt(rid),uid);
    }

    @Override
    public void cancelFavority(String rid, int uid) {
        ifd.cancelFavority(Integer.parseInt(rid),uid);
    }

    @Override
    public PageBean<Route> queryFavorite(int uid,int currentPage, int rows) {
        PageBean<Route> rpb = new PageBean<>();
        int totalRows = ifd.findRows(uid);
        rpb.setTotalRows(totalRows);
        rpb.setTotalRows(currentPage);
        rpb.setRows(rows);
        int totalPages = totalRows%rows==0?totalRows/rows:totalRows/rows+1;
        rpb.setTotalPage(totalPages);
        int start = (currentPage-1)*rows;
        List<Route> list = ifd.findRoutes(uid,start, rows);
        rpb.setList(list);
        return rpb;
    }


}
