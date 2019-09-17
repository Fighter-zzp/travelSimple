package com.zzp.travel.service;

import com.zzp.travel.entity.PageBean;
import com.zzp.travel.entity.Route;
import com.zzp.travel.entity.User;

public interface IFavoriteService {
    boolean isFavoriteService(int rid,int uid);

    void addFavorite(String rid, int uid);

    void cancelFavority(String rid, int uid);

    PageBean<Route> queryFavorite(int uid,int currentPage,int rows);
}
