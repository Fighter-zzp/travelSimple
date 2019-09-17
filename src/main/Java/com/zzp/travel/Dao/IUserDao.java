package com.zzp.travel.Dao;

import com.zzp.travel.entity.User;

public interface IUserDao {
    //查找对应的名字
    User findNanme(String name);
    //将用户信息保存下来
    void saveInfo(User user);
    //查看code是否存在
    User findByCode(String code);
    //设置用户的状态
    void updateStatus(Integer uid);

    User checkLogin(String username, String password);
}
