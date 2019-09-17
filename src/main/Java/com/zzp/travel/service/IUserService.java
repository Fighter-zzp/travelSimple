package com.zzp.travel.service;

import com.zzp.travel.entity.User;

public interface IUserService {
    boolean register(User user);

    boolean active(String code);

    User checkLogin(String username, String password);
}
