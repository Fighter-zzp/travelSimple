package com.zzp.travel.service.Impl;

import com.zzp.travel.Dao.ICategoryDao;
import com.zzp.travel.Dao.impl.CategoryDaoImpl;
import com.zzp.travel.entity.Category;
import com.zzp.travel.service.ICategoryService;
import com.zzp.travel.utils.JediUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements ICategoryService {
    private ICategoryDao icd = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        Jedis jd = JediUtils.getJedis();
//        Set<String> catePorys = jd.zrange("catePory", 0, -1);
        // 获取带有分数的catePory数据
        Set<Tuple> catePorys = jd.zrangeByScoreWithScores("catePory", 0, -1);
        //第一次时Redis数据库里面没有值
        List<Category> list = null;
        if (catePorys==null||catePorys.size()<1){
            //从数据库中获取
            list = icd.findAll();
            //把数据库所有值存到Redis数据库里
            for (Category category : list) {
                jd.zadd("catePory",category.getCid(),category.getCname());
            }
        }else {
            list = new ArrayList<>();
            for (Tuple key : catePorys) {
                Category c = new Category();
                c.setCid((int)key.getScore());
                c.setCname(key.getElement());
                list.add(c);
            }
        }
        return list;
    }
}
