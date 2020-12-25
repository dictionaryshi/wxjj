package com.wx.dao.warehouse.mapper.extend;

import com.wx.dao.warehouse.model.GoodsSkuDO;
import com.wx.dao.warehouse.model.extend.GoodsSkuDOExampleExtend;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 2:40 下午
 * ---------------------------------------
 * Desc    : GoodsSkuDOMapper扩展类
 */
public interface GoodsSkuDOMapperExtend {

    @SelectProvider(type = GoodsSkuDOSqlProviderExtend.class, method = "selectByExampleExtend")
    List<GoodsSkuDO> selectByExampleExtend(GoodsSkuDOExampleExtend example);
}
