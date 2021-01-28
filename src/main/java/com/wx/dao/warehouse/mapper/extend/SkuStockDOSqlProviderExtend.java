package com.wx.dao.warehouse.mapper.extend;

import com.scy.core.page.PageParam;
import com.wx.dao.warehouse.mapper.SkuStockDOSqlProvider;
import com.wx.dao.warehouse.model.extend.SkuStockDOExampleExtend;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 8:58 下午
 * ---------------------------------------
 * Desc    : SkuStockDOSqlProvider扩展
 */
public class SkuStockDOSqlProviderExtend extends SkuStockDOSqlProvider {

    public String selectByExampleExtend(SkuStockDOExampleExtend example) {
        String sql = super.selectByExample(example);
        return PageParam.appendOrderAndLimit(sql, example.getPageParam());
    }
}
