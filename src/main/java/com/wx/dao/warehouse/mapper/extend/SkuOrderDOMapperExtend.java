package com.wx.dao.warehouse.mapper.extend;

import com.wx.dao.warehouse.mapper.SkuOrderDOMapper;
import com.wx.dao.warehouse.model.SkuOrderDO;
import com.wx.dao.warehouse.model.extend.SkuOrderDOExampleExtend;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 11:07 上午
 * ---------------------------------------
 * Desc    : SkuOrderDOMapper扩展
 */
public interface SkuOrderDOMapperExtend extends SkuOrderDOMapper {

    @SelectProvider(type = SkuOrderDOSqlProviderExtend.class, method = "selectByPage")
    List<SkuOrderDO> selectByPage(SkuOrderDOExampleExtend example);
}
