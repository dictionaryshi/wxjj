package com.wx.dao.warehouse.mapper.extend;

import com.scy.core.page.PageParam;
import com.wx.dao.warehouse.mapper.SkuStockDetailDOSqlProvider;
import com.wx.dao.warehouse.model.extend.SkuStockDetailDOExampleExtend;

/**
 * @author : shichunyang
 * Date    : 2021/1/31
 * Time    : 3:14 下午
 * ---------------------------------------
 * Desc    : SkuStockDetailDOSqlProvider扩展
 */
public class SkuStockDetailDOSqlProviderExtend extends SkuStockDetailDOSqlProvider {

    public String selectByPage(SkuStockDetailDOExampleExtend example) {
        String sql = super.selectByExample(example);
        return PageParam.appendOrderAndLimit(sql, example.getPageParam());
    }
}
