package com.zzp.test;

import com.zzp.travel.Dao.ICategoryDao;
import com.zzp.travel.Dao.IFavoriteDao;
import com.zzp.travel.Dao.IUserDao;
import com.zzp.travel.Dao.impl.CategoryDaoImpl;
import com.zzp.travel.Dao.impl.FavorityDaoImpl;
import com.zzp.travel.Dao.impl.UserDaoImpl;
import com.zzp.travel.entity.Route;
import com.zzp.travel.entity.User;
import com.zzp.travel.service.Impl.FavoriteServiceImpl;
import com.zzp.travel.service.IFavoriteService;
import com.zzp.travel.service.IRouteService;
import com.zzp.travel.service.Impl.RouteServiceImpl;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Test1 {
    private IUserDao iu = new UserDaoImpl();
    @Test
    public void demo1() {
        User name = iu.findNanme("斯氏手水蚤");
        System.out.println(name);
    }
    @Test
    public void demo2(){
        User user = new User("小黄","1223","Army","2019-09-06","女","1532411","xxx@xx.x","t","a");
        iu.saveInfo(user);
        System.out.println(200);
    }

    @Test
    public void demo3(){
        User ass = iu.checkLogin("Ass", "123456");
        System.out.println(ass);
    }

    @Test
    public void demo4(){
        User user = new User();
        Class<? extends User> aClass = user.getClass();
        try {
            Method prints = aClass.getDeclaredMethod("prints");
            prints.setAccessible(true);
            Object invoke = prints.invoke(user);
            System.out.println(invoke);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo5(){
        ICategoryDao ic = new CategoryDaoImpl();
        System.out.println(ic.findAll());
    }

    @Test
    public void demo6(){
        IRouteService irs = new RouteServiceImpl();
//        PageBean<Route> routePageBean = irs.pageQuery(5, 0, 5, rname);
//        System.out.println(routePageBean);
//        IRouteDao ird = new RouteDaoImpl();
//        Route route = ird.queryOne(1);
//        System.out.println(route.getRouteImgList());
        Route route = irs.detailQuery(1);
        System.out.println(route.getRouteImgList());
    }
    @Test
    public void test1(){
        IFavoriteDao ifd = new FavorityDaoImpl();
        List<Route> routes = ifd.findRoutes(9, 0, 1);
        System.out.println(routes.get(0).getRname());
    }
}
