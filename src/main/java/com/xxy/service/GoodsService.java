package com.xxy.service;

import com.xxy.model.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsService {
    public List<Goods> getAllGoods(@Param("offser") Integer offser,@Param("limit") Integer limit);
    public Integer getGoodsNum(Integer id);
    public Integer updateGoodsNum(Integer id);
    public Goods getGoodInfo(Integer id);
    public Integer insertGood(Goods goods);
    public Integer getcount();
    public Integer updategood(Goods goods);
    @Delete("delete from goods where id=#{id}")
    public Integer delete_good(@Param("id") Integer id);
}
