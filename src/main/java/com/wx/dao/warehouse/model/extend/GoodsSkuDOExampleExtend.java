package com.wx.dao.warehouse.model.extend;

import com.scy.core.page.PageParam;
import com.wx.dao.warehouse.model.GoodsSkuDOExample;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 2:38 下午
 * ---------------------------------------
 * Desc    : GoodsSkuDOExample扩展类
 */
@Getter
@Setter
@ToString
public class GoodsSkuDOExampleExtend extends GoodsSkuDOExample {

    /**
     * 分页参数
     */
    private PageParam pageParam;
}
