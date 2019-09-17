package com.zzp.travel.Dao.impl;

import com.zzp.travel.Dao.IFavoriteDao;
import com.zzp.travel.entity.Favorite;
import com.zzp.travel.entity.Route;
import com.zzp.travel.entity.User;
import com.zzp.travel.utils.DBUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class FavorityDaoImpl implements IFavoriteDao {
    private JdbcTemplate jt = new JdbcTemplate(DBUtils.getDataSource());
    @Override
    public Favorite findFavorite(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "SELECT * FROM tab_favorite where rid=? and uid=?";
            favorite = jt.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {}
        return favorite;
    }

    @Override
    public int findCount(int rid) {
        String sql = "SELECT count(*) FROM tab_favorite where rid = ?";
        return jt.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql = "INSERT INTO tab_favorite(rid, date, uid) VALUES (?,?,?)";
        jt.update(sql,rid,new Date(),uid);
    }

    @Override
    public void cancelFavority(int rid, int uid) {
        String sql = "DELETE FROM tab_favorite WHERE rid = ? AND uid = ?";
        jt.update(sql, rid, uid);
    }

    @Override
    public int findRows(int uid) {
        int count = 0;
        try {
            String sql = "select count(*) from tab_favorite where uid = ?";
            count = jt.queryForObject(sql,Integer.class,uid);
        } catch (DataAccessException e) {}
        return count;
    }

    @Override
    public List<Route> findRoutes(int uid,int start, int rows) {
        String sql = "SELECT * FROM tab_favorite tf,tab_route tr where uid = ? and tf.rid=tr.rid limit ?,? ";
        return jt.query(sql,new BeanPropertyRowMapper<>(Route.class),uid,start,rows);
    }
}
