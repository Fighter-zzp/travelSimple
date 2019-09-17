package com.zzp.travel.Dao.impl;

import com.zzp.travel.Dao.ICategoryDao;
import com.zzp.travel.entity.Category;
import com.zzp.travel.utils.DBUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    private JdbcTemplate jt = new JdbcTemplate(DBUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM tab_category ORDER BY cid";
        return jt.query(sql,new BeanPropertyRowMapper<>(Category.class));
    }
}
