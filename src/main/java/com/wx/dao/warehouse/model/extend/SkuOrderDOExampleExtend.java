package com.wx.dao.warehouse.model.extend;

import com.scy.core.page.PageParam;
import com.wx.dao.warehouse.model.SkuOrderDOExample;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 12:23 上午
 * ---------------------------------------
 * Desc    : SkuOrderDOExample扩展
 */
@Getter
@Setter
@ToString
public class SkuOrderDOExampleExtend extends SkuOrderDOExample {

    /**
     * 分页参数
     */
    private PageParam pageParam;
}
