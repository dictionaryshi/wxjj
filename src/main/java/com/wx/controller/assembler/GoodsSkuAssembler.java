package com.wx.controller.assembler;

import com.scy.core.CollectionUtil;
import com.scy.core.ObjectUtil;
import com.scy.core.page.PageResult;
import com.wx.controller.request.goods.AddGoodsSkuRequest;
import com.wx.controller.request.goods.QueryGoodsSkuByPageRequest;
import com.wx.controller.request.goods.UpdateGoodsSkuRequest;
import com.wx.controller.response.goods.GoodsSkuResponse;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2020/12/25
 * Time    : 11:53 上午
 * ---------------------------------------
 * Desc    : 商品对象工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsSkuAssembler {

    public static GoodsSkuEntity toGoodsSkuEntity(AddGoodsSkuRequest addGoodsSkuRequest) {
        GoodsSkuEntity goodsSkuEntity = new GoodsSkuEntity();
        goodsSkuEntity.setSkuName(addGoodsSkuRequest.getSkuName());
        goodsSkuEntity.setCategoryId(addGoodsSkuRequest.getCategoryId());
        return goodsSkuEntity;
    }

    public static GoodsSkuEntity toGoodsSkuEntity(UpdateGoodsSkuRequest updateGoodsSkuRequest) {
        GoodsSkuEntity goodsSkuEntity = new GoodsSkuEntity();
        goodsSkuEntity.setSkuId(updateGoodsSkuRequest.getSkuId());
        goodsSkuEntity.setSkuName(updateGoodsSkuRequest.getSkuName());
        goodsSkuEntity.setCategoryId(updateGoodsSkuRequest.getCategoryId());
        return goodsSkuEntity;
    }

    public static GoodsSkuEntity toGoodsSkuEntity(QueryGoodsSkuByPageRequest queryGoodsSkuByPageRequest) {
        GoodsSkuEntity goodsSkuEntity = new GoodsSkuEntity();
        goodsSkuEntity.setSkuId(queryGoodsSkuByPageRequest.getSkuId());
        goodsSkuEntity.setSkuName(queryGoodsSkuByPageRequest.getSkuName());
        goodsSkuEntity.setCategoryId(queryGoodsSkuByPageRequest.getCategoryId());
        return goodsSkuEntity;
    }

    public static GoodsSkuResponse toGoodsSkuResponse(GoodsSkuEntity goodsSkuEntity) {
        if (ObjectUtil.isNull(goodsSkuEntity)) {
            return null;
        }

        GoodsSkuResponse goodsSkuResponse = new GoodsSkuResponse();
        goodsSkuResponse.setSkuId(goodsSkuEntity.getSkuId());
        goodsSkuResponse.setSkuName(goodsSkuEntity.getSkuName());
        goodsSkuResponse.setCategoryId(goodsSkuEntity.getCategoryId());
        goodsSkuResponse.setCategoryName(goodsSkuEntity.getCategoryName());
        return goodsSkuResponse;
    }

    public static PageResult<GoodsSkuResponse> toGoodsSkuResponsePageResult(PageResult<GoodsSkuEntity> pageResult) {
        PageResult<GoodsSkuResponse> goodsSkuResponsePageResult = new PageResult<>();
        goodsSkuResponsePageResult.setPage(pageResult.getPage());
        goodsSkuResponsePageResult.setLimit(pageResult.getLimit());
        goodsSkuResponsePageResult.setTotal(pageResult.getTotal());
        goodsSkuResponsePageResult.setDatas(CollectionUtil.map(pageResult.getDatas(), GoodsSkuAssembler::toGoodsSkuResponse).collect(Collectors.toList()));
        return goodsSkuResponsePageResult;
    }
}
