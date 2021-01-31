package com.wx.dao.warehouse.model.extend;

import com.scy.core.page.PageParam;
import com.wx.dao.warehouse.model.SkuStockDetailDOExample;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2021/1/31
 * Time    : 3:12 下午
 * ---------------------------------------
 * Desc    : SkuStockDetailDOExample扩展
 */
@Getter
@Setter
@ToString
public class SkuStockDetailDOExampleExtend extends SkuStockDetailDOExample {

    /**
     * 分页参数
     */
    private PageParam pageParam;
}
