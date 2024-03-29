package com.wx.controller.assembler;

import com.scy.core.StringUtil;
import com.scy.core.format.DateUtil;
import com.scy.core.format.NumberUtil;
import com.scy.core.page.PageResult;
import com.wx.controller.request.stock.QuerySkuStockByPageRequest;
import com.wx.controller.request.stock.QueryStockDetailByPageRequest;
import com.wx.controller.request.stock.UpdateStockRequest;
import com.wx.controller.response.stock.SkuStockDetailResponse;
import com.wx.controller.response.stock.SkuStockResponse;
import com.wx.controller.response.stock.StockChangeResponse;
import com.wx.domain.stock.entity.SkuStockDetailEntity;
import com.wx.domain.stock.entity.SkuStockEntity;
import com.wx.domain.stock.entity.StockOperateValueobject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2021/1/30
 * Time    : 9:23 下午
 * ---------------------------------------
 * Desc    : 商品库存Assembler
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkuStockAssembler {

    public static SkuStockEntity toSkuStockEntity(UpdateStockRequest updateStockRequest) {
        SkuStockEntity skuStockEntity = new SkuStockEntity();
        skuStockEntity.setStockBaseInfoId(updateStockRequest.getStockBaseInfoId());
        skuStockEntity.setSkuId(updateStockRequest.getSkuId());
        skuStockEntity.setStock(updateStockRequest.getStock());
        return skuStockEntity;
    }

    public static StockChangeResponse toStockChangeResponse(SkuStockEntity skuStockEntity) {
        StockOperateValueobject stockOperateValueobject = skuStockEntity.getStockOperateValueobject();

        StockChangeResponse stockChangeResponse = new StockChangeResponse();
        stockChangeResponse.setStockOffset(stockOperateValueobject.getStockOffset());
        stockChangeResponse.setStockBefore(stockOperateValueobject.getStockBefore());
        stockChangeResponse.setStockAfter(stockOperateValueobject.getStockAfter());
        return stockChangeResponse;
    }

    public static SkuStockEntity toSkuStockEntity(QuerySkuStockByPageRequest querySkuStockByPageRequest) {
        SkuStockEntity skuStockEntity = new SkuStockEntity();
        skuStockEntity.setStockBaseInfoId(querySkuStockByPageRequest.getStockBaseInfoId());
        skuStockEntity.setSkuName(querySkuStockByPageRequest.getSkuName());
        return skuStockEntity;
    }

    public static SkuStockResponse toSkuStockResponse(SkuStockEntity skuStockEntity) {
        if (Objects.isNull(skuStockEntity)) {
            return null;
        }

        SkuStockResponse skuStockResponse = new SkuStockResponse();
        skuStockResponse.setStockBaseInfoId(skuStockEntity.getStockBaseInfoId());
        skuStockResponse.setSkuId(skuStockEntity.getSkuId());
        skuStockResponse.setStock(skuStockEntity.getStock());
        skuStockResponse.setStockName(skuStockEntity.getStockName());
        skuStockResponse.setSkuName(skuStockEntity.getSkuName());
        return skuStockResponse;
    }

    public static PageResult<SkuStockResponse> toSkuStockResponse(PageResult<SkuStockEntity> skuStockEntityPageResult) {
        PageResult<SkuStockResponse> pageResult = new PageResult<>();
        pageResult.setPage(skuStockEntityPageResult.getPage());
        pageResult.setLimit(skuStockEntityPageResult.getLimit());
        pageResult.setTotal(skuStockEntityPageResult.getTotal());
        pageResult.setMaxPage(skuStockEntityPageResult.getMaxPage());
        pageResult.setDatas(skuStockEntityPageResult.getDatas().stream().map(SkuStockAssembler::toSkuStockResponse).collect(Collectors.toList()));
        return pageResult;
    }

    public static SkuStockDetailEntity toSkuStockDetailEntity(QueryStockDetailByPageRequest queryStockDetailByPageRequest) {
        SkuStockDetailEntity skuStockDetailEntity = new SkuStockDetailEntity();
        skuStockDetailEntity.setStockBaseInfoId(queryStockDetailByPageRequest.getStockBaseInfoId());
        skuStockDetailEntity.setOrderId(StringUtil.isEmpty(queryStockDetailByPageRequest.getOrderId()) ? null : Long.parseLong(queryStockDetailByPageRequest.getOrderId()));
        skuStockDetailEntity.setSkuName(queryStockDetailByPageRequest.getSkuName());
        skuStockDetailEntity.setCreatedAtStart(DateUtil.str2Date(queryStockDetailByPageRequest.getStartTime(), DateUtil.PATTERN_SECOND));
        skuStockDetailEntity.setCreatedAtEnd(DateUtil.str2Date(queryStockDetailByPageRequest.getEndTime(), DateUtil.PATTERN_SECOND));
        return skuStockDetailEntity;
    }

    public static SkuStockDetailResponse toSkuStockDetailResponse(SkuStockDetailEntity skuStockDetailEntity) {
        SkuStockDetailResponse skuStockDetailResponse = new SkuStockDetailResponse();
        skuStockDetailResponse.setStockOffset(skuStockDetailEntity.getStockOffset());
        skuStockDetailResponse.setStockBefore(skuStockDetailEntity.getStockBefore());
        skuStockDetailResponse.setStockAfter(skuStockDetailEntity.getStockAfter());
        skuStockDetailResponse.setOrderId((Objects.isNull(skuStockDetailEntity.getOrderId()) || skuStockDetailEntity.getOrderId() <= NumberUtil.ZERO.longValue())
                ? StringUtil.EMPTY : String.valueOf(skuStockDetailEntity.getOrderId()));
        skuStockDetailResponse.setCreatedAt(skuStockDetailEntity.getCreatedAt());
        skuStockDetailResponse.setStockBaseInfoName(skuStockDetailEntity.getStockBaseInfoName());
        skuStockDetailResponse.setSkuName(skuStockDetailEntity.getSkuName());
        skuStockDetailResponse.setTypeDesc(skuStockDetailEntity.getTypeDesc());
        skuStockDetailResponse.setOperatorName(skuStockDetailEntity.getOperatorName());
        return skuStockDetailResponse;
    }

    public static PageResult<SkuStockDetailResponse> toSkuStockDetailResponse(PageResult<SkuStockDetailEntity> skuStockDetailEntityPageResult) {
        PageResult<SkuStockDetailResponse> pageResult = new PageResult<>();
        pageResult.setPage(skuStockDetailEntityPageResult.getPage());
        pageResult.setLimit(skuStockDetailEntityPageResult.getLimit());
        pageResult.setTotal(skuStockDetailEntityPageResult.getTotal());
        pageResult.setMaxPage(skuStockDetailEntityPageResult.getMaxPage());
        pageResult.setDatas(skuStockDetailEntityPageResult.getDatas().stream().map(SkuStockAssembler::toSkuStockDetailResponse).collect(Collectors.toList()));
        return pageResult;
    }
}
