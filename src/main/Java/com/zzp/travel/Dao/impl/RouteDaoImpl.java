package com.zzp.travel.Dao.impl;

import com.zzp.travel.Dao.IRouteDao;
import com.zzp.travel.entity.Route;
import com.zzp.travel.utils.DBUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements IRouteDao {
    private JdbcTemplate jt = new JdbcTemplate(DBUtils.getDataSource());

    /**
     * 寻找cid下的总条数
     * @param cid
     * @param rname
     * @return
     */
    @Override
    public Integer findTotalCountByCid(int cid, String rname){
//        String sql = "select count(*) from tab_route where cid = ?";
        String sql = "select count(*) from tab_route where 1 = 1";
//         jt.queryForObject(sql, Integer.class, cid);
        StringBuilder sb = new StringBuilder(sql);//储存sql
        List parms = new ArrayList();//储存参数集
        if (cid>0){
            sb.append(" and cid = ? ");
            parms.add(cid);
        }
        if (rname!=null&& ! "".equals(rname)){
            sb.append(" and rname like ? ");
            parms.add("%"+rname+"%");
        }
        return jt.queryForObject(sb.toString(),Integer.class,parms.toArray());
    }

    /**
     * 由cid与start，rows寻找分页信息
     * @param cid
     * @param rname
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<Route> queryList(int cid, String rname, int start, int rows){
//        String sql = "select * from tab_route where cid = ? limit ? , ?";
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);//储存sql
        List parms = new ArrayList();//储存参数集
        if (cid>0){
            sb.append(" and cid = ? ");
            parms.add(cid);
        }
        if (rname!=null&& ! "".equals(rname)){
            sb.append(" and rname like ? ");
            parms.add("%"+rname+"%");
        }
        sb.append(" limit ?,?");
        parms.add(start);
        parms.add(rows);
        return jt.query(sb.toString(), new BeanPropertyRowMapper<>(Route.class), parms.toArray());
    }

    @Override
    public Route queryOne(int rid) {
        String sql = "SELECT * FROM tab_route where rid = ?";
        return jt.queryForObject(sql,new BeanPropertyRowMapper<>(Route.class),rid);
    }
}
