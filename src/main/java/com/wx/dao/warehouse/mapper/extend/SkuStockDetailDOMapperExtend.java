package com.wx.dao.warehouse.mapper.extend;

import com.wx.dao.warehouse.mapper.SkuStockDetailDOMapper;
import com.wx.dao.warehouse.model.SkuStockDetailDO;
import com.wx.dao.warehouse.model.extend.SkuStockDetailDOExampleExtend;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2021/1/31
 * Time    : 3:14 下午
 * ---------------------------------------
 * Desc    : SkuStockDetailDOMapper扩展
 */
public interface SkuStockDetailDOMapperExtend extends SkuStockDetailDOMapper {

    @SelectProvider(type = SkuStockDetailDOSqlProviderExtend.class, method = "selectByPage")
    List<SkuStockDetailDO> selectByPage(SkuStockDetailDOExampleExtend example);
}
