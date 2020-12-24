package com.wx.domain.category.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : shichunyang
 * Date    : 2020/12/23
 * Time    : 10:03 下午
 * ---------------------------------------
 * Desc    : 商品分类
 */
@Getter
@Setter
@ToString
public class SkuCategoryEntity {

    /**
     * 品类id
     */
    private Long id;

    /**
     * 品类名称
     */
    private String categoryName;
}
