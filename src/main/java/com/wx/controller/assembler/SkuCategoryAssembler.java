package com.wx.controller.assembler;

import com.scy.core.ObjectUtil;
import com.wx.controller.response.category.SkuCategoryResponse;
import com.wx.domain.category.entity.SkuCategoryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : shichunyang
 * Date    : 2020/12/24
 * Time    : 1:13 下午
 * ---------------------------------------
 * Desc    : 品类对象转化类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkuCategoryAssembler {

    public static SkuCategoryResponse toSkuCategoryResponse(SkuCategoryEntity skuCategoryEntity) {
        if (ObjectUtil.isNull(skuCategoryEntity)) {
            return null;
        }

        SkuCategoryResponse skuCategoryResponse = new SkuCategoryResponse();
        skuCategoryResponse.setCategoryId(skuCategoryEntity.getId());
        skuCategoryResponse.setCategoryName(skuCategoryEntity.getCategoryName());
        return skuCategoryResponse;
    }
}
