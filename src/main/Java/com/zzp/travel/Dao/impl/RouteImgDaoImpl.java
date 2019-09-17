package com.zzp.travel.Dao.impl;

import com.zzp.travel.Dao.IRouteImgDao;
import com.zzp.travel.entity.RouteImg;
import com.zzp.travel.utils.DBUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements IRouteImgDao {
    JdbcTemplate jt = new JdbcTemplate(DBUtils.getDataSource());
    @Override
    public List<RouteImg> findAll(int rid) {
        String sql = "SELECT * FROM tab_route_img where rid = ?";
        return jt.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }
}
