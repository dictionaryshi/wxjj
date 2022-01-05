package com.wx.dao.warehouse.mapper.extend;

import com.google.common.collect.Lists;
import com.scy.core.db.SqlUtil;
import com.wx.dao.warehouse.model.SkuStockDetailDO;

import java.util.List;

/**
 * @author : shichunyang
 * Date    : 2021/11/8
 * Time    : 7:11 下午
 * ---------------------------------------
 * Desc    :
 */
public class A {

    public String batchInsert(List<SkuStockDetailDO> skuStockDetails) {
        return SqlUtil.batchInsert(SkuStockDetailDO.class, "sku_stock_detail", Lists.newArrayList("id", "createdAt", "updatedAt"));
    }

    public static void main(String[] args) {
        System.out.println(SqlUtil.batchInsert(SkuStockDetailDO.class, "sku_stock_detail", Lists.newArrayList("id", "createdAt", "updatedAt")));
    }
}
