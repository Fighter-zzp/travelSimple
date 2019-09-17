package com.zzp.travel.Dao.impl;

import com.zzp.travel.Dao.IUserDao;
import com.zzp.travel.entity.User;
import com.zzp.travel.utils.DBUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements IUserDao {
    private JdbcTemplate jt = new JdbcTemplate(DBUtils.getDataSource());
    @Override
    public User findNanme(String name){
        User user = null;
        String sql = "SELECT * FROM tab_user where username=?";
        //当usename不在时会抛异常所有，捕获它禁止它跑
        try {
            user=jt.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),name);
        }catch (Exception e){
            //System.out.println("用户名已存在！");
        }
        return user;
    }

    @Override
    public void saveInfo(User user){
        //1.定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql
        jt.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user=jt.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),code);
        } catch (Exception e) {}
        return user;
    }

    @Override
    public void updateStatus(Integer uid) {
        String sql = "UPDATE tab_user SET  status = 'Y' WHERE uid = ?";
        jt.update(sql,uid);
    }

    @Override
    public User checkLogin(String username, String password) {
        User user=null;
        String sql = "select * from tab_user where username=? and password=?";
        try {
            user = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {}
        return user;
    }
}
