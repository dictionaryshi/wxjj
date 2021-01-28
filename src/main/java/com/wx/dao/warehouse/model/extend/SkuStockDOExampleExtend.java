package com.wx.dao.warehouse.model.extend;

import com.scy.core.page.PageParam;
import com.wx.dao.warehouse.model.SkuStockDOExample;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 8:53 下午
 * ---------------------------------------
 * Desc    : SkuStockDOExample扩展
 */
@Getter
@Setter
@ToString
public class SkuStockDOExampleExtend extends SkuStockDOExample {

    /**
     * 分页参数
     */
    private PageParam pageParam;
}
