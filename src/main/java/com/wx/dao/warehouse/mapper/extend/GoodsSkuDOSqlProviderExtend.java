package com.wx.dao.warehouse.mapper.extend;

import com.scy.core.page.PageParam;
import com.wx.dao.warehouse.mapper.GoodsSkuDOSqlProvider;
import com.wx.dao.warehouse.model.extend.GoodsSkuDOExampleExtend;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 2:42 下午
 * ---------------------------------------
 * Desc    : GoodsSkuDOSqlProvider扩展类
 */
public class GoodsSkuDOSqlProviderExtend extends GoodsSkuDOSqlProvider {

    public String selectByExampleExtend(GoodsSkuDOExampleExtend example) {
        String sql = super.selectByExample(example);
        return PageParam.appendOrderAndLimit(sql, example.getPageParam());
    }
}
