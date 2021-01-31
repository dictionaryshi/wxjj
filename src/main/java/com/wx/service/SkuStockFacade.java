package com.wx.service;

import com.scy.core.CollectionUtil;
import com.scy.core.StringUtil;
import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.wx.domain.passport.service.UserPassportDomainService;
import com.wx.domain.sku.service.GoodsSkuDomainService;
import com.wx.domain.stock.entity.SkuStockDetailEntity;
import com.wx.domain.stock.entity.SkuStockEntity;
import com.wx.domain.stock.service.SkuStockDomainService;
import com.wx.domain.stock.service.StockBaseInfoDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2021/1/30
 * Time    : 8:29 下午
 * ---------------------------------------
 * Desc    : 库存facade
 */
@Service
public class SkuStockFacade {

    @Autowired
    private SkuStockDomainService skuStockDomainService;

    @Autowired
    private StockBaseInfoDomainService stockBaseInfoDomainService;

    @Autowired
    private GoodsSkuDomainService goodsSkuDomainService;

    @Autowired
    private UserPassportDomainService userPassportDomainService;

    /**
     * 盘点库存
     */
    public SkuStockEntity updateStock(SkuStockEntity skuStockEntity) {
        return skuStockDomainService.updateStock(skuStockEntity);
    }

    /**
     * 分页查询库存
     */
    public PageResult<SkuStockEntity> listByPage(PageParam pageParam, SkuStockEntity skuStockEntity) {
        if (!StringUtil.isEmpty(skuStockEntity.getSkuName())) {
            List<Long> skuIds = goodsSkuDomainService.listIdsByName(skuStockEntity.getSkuName());
            if (CollectionUtil.isEmpty(skuIds)) {
                skuIds = Collections.singletonList(-1L);
            }
            skuStockEntity.setSkuIds(skuIds);
        }

        PageResult<SkuStockEntity> pageResult = skuStockDomainService.listByPage(pageParam, skuStockEntity);
        List<SkuStockEntity> datas = CollectionUtil.emptyIfNull(pageResult.getDatas());
        pageResult.setDatas(datas);

        List<Long> stockBaseInfoIds = datas.stream().map(SkuStockEntity::getStockBaseInfoId).distinct().collect(Collectors.toList());
        Map<Long, String> stockNameMap = stockBaseInfoDomainService.getStockNameMap(stockBaseInfoIds);

        List<Long> skuIds = datas.stream().map(SkuStockEntity::getSkuId).distinct().collect(Collectors.toList());
        Map<Long, String> skuNameMap = goodsSkuDomainService.getSkuNameMap(skuIds);

        datas.forEach(skuStock -> {
            skuStock.setStockName(stockNameMap.get(skuStock.getStockBaseInfoId()));
            skuStock.setSkuName(skuNameMap.get(skuStock.getSkuId()));
        });

        return pageResult;
    }

    /**
     * 分页查询库存操作记录
     */
    public PageResult<SkuStockDetailEntity> listStockDetailByPage(PageParam pageParam, SkuStockDetailEntity skuStockDetailEntity) {
        if (!StringUtil.isEmpty(skuStockDetailEntity.getSkuName())) {
            List<Long> skuIds = goodsSkuDomainService.listIdsByName(skuStockDetailEntity.getSkuName());
            if (CollectionUtil.isEmpty(skuIds)) {
                skuIds = Collections.singletonList(-1L);
            }
            skuStockDetailEntity.setSkuIds(skuIds);
        }

        PageResult<SkuStockDetailEntity> pageResult = skuStockDomainService.listStockDetailByPage(pageParam, skuStockDetailEntity);
        List<SkuStockDetailEntity> datas = CollectionUtil.emptyIfNull(pageResult.getDatas());
        pageResult.setDatas(datas);

        List<Long> stockBaseInfoIds = datas.stream().map(SkuStockDetailEntity::getStockBaseInfoId).distinct().collect(Collectors.toList());
        Map<Long, String> stockNameMap = stockBaseInfoDomainService.getStockNameMap(stockBaseInfoIds);

        List<Long> skuIds = datas.stream().map(SkuStockDetailEntity::getSkuId).distinct().collect(Collectors.toList());
        Map<Long, String> skuNameMap = goodsSkuDomainService.getSkuNameMap(skuIds);

        Map<Long, String> allPassportMap = userPassportDomainService.getAllPassportMap();

        datas.forEach(stockDetail -> {
            stockDetail.setStockBaseInfoName(stockNameMap.get(stockDetail.getStockBaseInfoId()));
            stockDetail.setSkuName(skuNameMap.get(stockDetail.getSkuId()));
            stockDetail.setOperatorName(allPassportMap.get(stockDetail.getOperator()));
        });

        return pageResult;
    }
}
