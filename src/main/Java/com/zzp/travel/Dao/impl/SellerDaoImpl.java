package com.zzp.travel.Dao.impl;

import com.zzp.travel.Dao.ISellerDao;
import com.zzp.travel.entity.Seller;
import com.zzp.travel.utils.DBUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements ISellerDao {
    JdbcTemplate jt = new JdbcTemplate(DBUtils.getDataSource());
    @Override
    public Seller findOne(int sid) {
        String sql = "select * from tab_seller where sid = ? ";
        return jt.queryForObject(sql,new BeanPropertyRowMapper<>(Seller.class),sid);
    }
}
