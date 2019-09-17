package com.zzp.travel.service;

import com.zzp.travel.entity.PageBean;
import com.zzp.travel.entity.Route;

public interface IRouteService {
    PageBean<Route> pageQuery(int cid, int currentPage, int rows, String rname);

    Route detailQuery(int rid);
}
