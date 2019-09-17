package com.zzp.travel.Dao;

import com.zzp.travel.entity.Seller;

public interface ISellerDao {
    Seller findOne(int sid);
}
