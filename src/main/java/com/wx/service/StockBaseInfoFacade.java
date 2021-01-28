package com.wx.service;

import com.scy.core.model.DiffBO;
import com.wx.domain.stock.entity.StockBaseInfoEntity;
import com.wx.domain.stock.service.StockBaseInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : shichunyang
 * Date    : 2021/1/28
 * Time    : 10:26 上午
 * ---------------------------------------
 * Desc    : 仓库基本信息
 */
@Slf4j
@Service
public class StockBaseInfoFacade {

    @Autowired
    private StockBaseInfoDomainService stockBaseInfoDomainService;

    /**
     * 插入仓库基本信息
     */
    public long insert(StockBaseInfoEntity stockBaseInfoEntity) {
        return stockBaseInfoDomainService.insertStockBaseInfoEntity(stockBaseInfoEntity);
    }

    /**
     * 查询仓库基本信息
     */
    public Optional<StockBaseInfoEntity> get(long id) {
        return stockBaseInfoDomainService.getStockBaseInfoEntity(id);
    }

    /**
     * 查询所有仓库基本信息
     */
    public List<StockBaseInfoEntity> listAll() {
        return stockBaseInfoDomainService.listAllStockBaseInfoEntities();
    }

    /**
     * 修改仓库基本信息
     */
    public List<DiffBO> update(StockBaseInfoEntity stockBaseInfoEntity) {
        return stockBaseInfoDomainService.updateStockBaseInfo(stockBaseInfoEntity);
    }
}
