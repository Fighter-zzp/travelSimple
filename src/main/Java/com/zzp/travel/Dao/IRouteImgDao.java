package com.zzp.travel.Dao;

import com.zzp.travel.entity.RouteImg;

import java.util.List;

public interface IRouteImgDao {
    List<RouteImg> findAll(int rid);
}
