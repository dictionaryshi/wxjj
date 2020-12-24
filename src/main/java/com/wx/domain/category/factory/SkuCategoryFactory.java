package com.wx.domain.category.factory;

import com.scy.core.ObjectUtil;
import com.wx.dao.warehouse.model.SkuCategoryDO;
import com.wx.domain.category.entity.SkuCategoryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : shichunyang
 * Date    : 2020/12/23
 * Time    : 10:09 下午
 * ---------------------------------------
 * Desc    : 品类工厂
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkuCategoryFactory {

    public static SkuCategoryEntity toSkuCategoryEntity(SkuCategoryDO skuCategoryDO) {
        if (ObjectUtil.isNull(skuCategoryDO)) {
            return null;
        }

        SkuCategoryEntity skuCategoryEntity = new SkuCategoryEntity();
        skuCategoryEntity.setId(skuCategoryDO.getId());
        skuCategoryEntity.setCategoryName(skuCategoryDO.getCategoryName());
        return skuCategoryEntity;
    }
}
