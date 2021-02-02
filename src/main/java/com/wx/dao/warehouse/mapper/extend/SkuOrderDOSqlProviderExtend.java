package com.wx.dao.warehouse.mapper.extend;

import com.scy.core.page.PageParam;
import com.wx.dao.warehouse.mapper.SkuOrderDOSqlProvider;
import com.wx.dao.warehouse.model.extend.SkuOrderDOExampleExtend;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 11:04 上午
 * ---------------------------------------
 * Desc    : SkuOrderDOSqlProvider扩展
 */
public class SkuOrderDOSqlProviderExtend extends SkuOrderDOSqlProvider {

    public String selectByPage(SkuOrderDOExampleExtend example) {
        String sql = super.selectByExample(example);
        return PageParam.appendOrderAndLimit(sql, example.getPageParam());
    }
}
