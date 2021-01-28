package com.wx.dao.warehouse.mapper.extend;

import com.wx.dao.warehouse.mapper.SkuStockDOMapper;
import com.wx.dao.warehouse.model.SkuStockDO;
import com.wx.dao.warehouse.model.extend.SkuStockDOExampleExtend;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 8:54 下午
 * ---------------------------------------
 * Desc    : SkuStockDOMapper扩展
 */
public interface SkuStockDOMapperExtend extends SkuStockDOMapper {

    @SelectProvider(type = SkuStockDOSqlProviderExtend.class, method = "selectByExampleExtend")
    List<SkuStockDO> selectByExampleExtend(SkuStockDOExampleExtend example);
}
