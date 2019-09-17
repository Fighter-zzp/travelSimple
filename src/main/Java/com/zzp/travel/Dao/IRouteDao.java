package com.zzp.travel.Dao;

import com.zzp.travel.entity.Route;

import java.util.List;

public interface IRouteDao {
    Integer findTotalCountByCid(int cid, String rname);
    List<Route> queryList(int cid, String rname, int start, int rows);

    Route queryOne(int rid);
}
