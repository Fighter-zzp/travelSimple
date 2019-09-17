package com.zzp.travel.service.Impl;

import com.zzp.travel.Dao.IUserDao;
import com.zzp.travel.Dao.impl.UserDaoImpl;
import com.zzp.travel.entity.User;
import com.zzp.travel.service.IUserService;
import com.zzp.travel.utils.MailUtils;
import com.zzp.travel.utils.UuidUtil;

public class UserServiceImpl implements IUserService {
    private IUserDao iu = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        User flag = iu.findNanme(user.getUsername());
//        System.out.println(flag);
        if (flag!=null)  return false;

        //设置user的激活码和状态值
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        iu.saveInfo(user);
        //发送邮件请求用户激活
        String context = "<a href='http://localhost:8080/travel/UserServlet?action=activeUser&code="+user.getCode()+
                "'>点击激活</a>-->进入旅游网登录页面！";
        System.out.println(user.getEmail());
        MailUtils.sendMail(user.getEmail(), context, "激活邮件");
        return true;
    }

    //激活登录
    @Override
    public boolean active(String code) {
        User user = iu.findByCode(code);
        if (user==null) return false;
        iu.updateStatus(user.getUid());
        return true;
    }

    // 登录检验
    @Override
    public User checkLogin(String username, String password) {
        return iu.checkLogin(username, password);
    }

}
