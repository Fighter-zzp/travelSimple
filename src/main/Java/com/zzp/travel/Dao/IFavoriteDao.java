package com.zzp.travel.Dao;

import com.zzp.travel.entity.Favorite;
import com.zzp.travel.entity.Route;
import com.zzp.travel.entity.User;

import java.util.List;

public interface IFavoriteDao {
    Favorite findFavorite(int rid, int uid);//获取单个收藏信息

    int findCount(int rid);//获取收藏个数

    void addFavorite(int rid, int uid);//添加收藏信息

    void cancelFavority(int rid, int uid);//取消收藏

    int findRows(int uid);

    List<Route> findRoutes(int uid,int start, int rows);
}
