package com.zzp.travel.Dao;

import com.zzp.travel.entity.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> findAll();
}
