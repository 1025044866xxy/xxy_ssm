package com.xxy.service.Impl;

import com.xxy.dao.GoodsDao;
import com.xxy.model.Goods;
import com.xxy.service.GoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;


    @Override
    public List<Goods> getAllGoods(Integer offser, Integer limit) {
        return goodsDao.getAllGoods(offser,limit);
    }

    @Override
    public Integer getGoodsNum(Integer id) {
        return goodsDao.getGoodsNum(id);
    }

    @Override
    public Integer updateGoodsNum(Integer id) {
        return goodsDao.updateGoodsNum(id);
    }

    @Override
    public Goods getGoodInfo(Integer id) {
        return goodsDao.getGoodInfo(id);
    }

    @Override
    public Integer insertGood(Goods goods) {
        return goodsDao.insertGood(goods);
    }

    @Override
    public Integer getcount() {
        return goodsDao.getcount();
    }

    @Override
    public Integer updategood(Goods goods) {
        return goodsDao.updategood(goods);
    }




    @Override
    public Integer delete_good(Integer id) {
        return goodsDao.delete_good(id);
    }

}
