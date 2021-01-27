package com.wx.domain.stock.service;

import com.scy.core.CollectionUtil;
import com.scy.core.DiffUtil;
import com.scy.core.StringUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.scy.core.model.DiffBO;
import com.scy.db.util.ForceMasterHelper;
import com.wx.dao.warehouse.mapper.StockBaseInfoDOMapper;
import com.wx.dao.warehouse.model.StockBaseInfoDO;
import com.wx.dao.warehouse.model.StockBaseInfoDOExample;
import com.wx.domain.stock.entity.StockBaseInfoEntity;
import com.wx.domain.stock.factory.StockBaseInfoFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2021/1/27
 * Time    : 2:26 下午
 * ---------------------------------------
 * Desc    : 仓库基本信息领域服务
 */
@Slf4j
@Service
public class StockBaseInfoDomainService {

    @Autowired
    private StockBaseInfoDOMapper stockBaseInfoDOMapper;

    public Optional<StockBaseInfoEntity> getStockBaseInfoEntity(long id) {
        StockBaseInfoDO stockBaseInfoDO = stockBaseInfoDOMapper.selectByPrimaryKey(id);
        return StockBaseInfoFactory.toStockBaseInfoEntity(stockBaseInfoDO);
    }

    public String getStockName(long id) {
        Optional<StockBaseInfoEntity> stockBaseInfoEntity = getStockBaseInfoEntity(id);
        if (!stockBaseInfoEntity.isPresent()) {
            return StringUtil.EMPTY;
        }

        return stockBaseInfoEntity.get().getName();
    }

    public Optional<StockBaseInfoEntity> getStockBaseInfoEntity(String name) {
        StockBaseInfoDOExample stockBaseInfoDOExample = new StockBaseInfoDOExample();
        StockBaseInfoDOExample.Criteria criteria = stockBaseInfoDOExample.createCriteria();
        criteria.andNameEqualTo(name);

        List<StockBaseInfoDO> stockBaseInfos = stockBaseInfoDOMapper.selectByExample(stockBaseInfoDOExample);
        if (CollectionUtil.isEmpty(stockBaseInfos)) {
            return Optional.empty();
        }

        StockBaseInfoDO stockBaseInfoDO = CollectionUtil.firstElement(stockBaseInfos);
        return StockBaseInfoFactory.toStockBaseInfoEntity(stockBaseInfoDO);
    }

    public List<StockBaseInfoEntity> listStockBaseInfoEntities(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return CollectionUtil.emptyList();
        }

        StockBaseInfoDOExample stockBaseInfoDOExample = new StockBaseInfoDOExample();
        StockBaseInfoDOExample.Criteria criteria = stockBaseInfoDOExample.createCriteria();
        criteria.andIdIn(ids);
        List<StockBaseInfoDO> stockBaseInfos = stockBaseInfoDOMapper.selectByExample(stockBaseInfoDOExample);
        return stockBaseInfos.stream().map(StockBaseInfoFactory::toStockBaseInfoEntity).map(Optional::get).collect(Collectors.toList());
    }

    public Map<Long, StockBaseInfoEntity> getStockBaseInfoMap(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return CollectionUtil.emptyMap();
        }

        List<StockBaseInfoEntity> stockBaseInfoEntities = listStockBaseInfoEntities(ids);
        return stockBaseInfoEntities.stream().collect(Collectors.toMap(StockBaseInfoEntity::getId, Function.identity(), (oldValue, newValue) -> newValue));
    }

    public List<StockBaseInfoEntity> listAllStockBaseInfoEntities() {
        StockBaseInfoDOExample stockBaseInfoDOExample = new StockBaseInfoDOExample();
        StockBaseInfoDOExample.Criteria criteria = stockBaseInfoDOExample.createCriteria();
        criteria.andIdGreaterThan(0L);
        List<StockBaseInfoDO> stockBaseInfos = stockBaseInfoDOMapper.selectByExample(stockBaseInfoDOExample);
        return stockBaseInfos.stream().map(StockBaseInfoFactory::toStockBaseInfoEntity).map(Optional::get).collect(Collectors.toList());
    }

    public long insertStockBaseInfoEntity(StockBaseInfoEntity stockBaseInfoEntity) {
        boolean isPresent = getStockBaseInfoEntity(stockBaseInfoEntity.getName()).isPresent();
        if (isPresent) {
            throw new BusinessException("仓库名称已存在");
        }

        Optional<StockBaseInfoDO> stockBaseInfoOptional = StockBaseInfoFactory.toStockBaseInfoDO(stockBaseInfoEntity);
        if (!stockBaseInfoOptional.isPresent()) {
            throw new BusinessException("仓库实体不存在");
        }

        StockBaseInfoDO stockBaseInfoDO = stockBaseInfoOptional.get();
        stockBaseInfoDOMapper.insertSelective(stockBaseInfoDO);
        return stockBaseInfoDO.getId();
    }

    public List<DiffBO> updateStockBaseInfo(StockBaseInfoEntity stockBaseInfoEntity) {
        Optional<StockBaseInfoEntity> stockBaseInfoEntityOptional = getStockBaseInfoEntity(stockBaseInfoEntity.getId());
        if (!stockBaseInfoEntityOptional.isPresent()) {
            throw new BusinessException(MessageUtil.format("仓库基础信息不存在", "id", stockBaseInfoEntity.getId()));
        }

        Optional<StockBaseInfoEntity> stockBaseInfoEntityByNameOptional = getStockBaseInfoEntity(stockBaseInfoEntity.getName());
        if (stockBaseInfoEntityByNameOptional.isPresent() && !Objects.equals(stockBaseInfoEntityOptional.get().getName(), stockBaseInfoEntity.getName())) {
            return CollectionUtil.emptyList();
        }

        Optional<StockBaseInfoDO> stockBaseInfoOptional = StockBaseInfoFactory.toStockBaseInfoDO(stockBaseInfoEntity);
        if (!stockBaseInfoOptional.isPresent()) {
            return CollectionUtil.emptyList();
        }

        stockBaseInfoDOMapper.updateByPrimaryKeySelective(stockBaseInfoOptional.get());

        Optional<StockBaseInfoEntity> afterStockBaseInfoEntityOptional;
        try {
            ForceMasterHelper.forceMaster();
            afterStockBaseInfoEntityOptional = getStockBaseInfoEntity(stockBaseInfoEntity.getId());
        } finally {
            ForceMasterHelper.clearForceMaster();
        }

        return afterStockBaseInfoEntityOptional.map(afterBaseInfoEntity -> DiffUtil.diff(stockBaseInfoEntityOptional.get(), afterBaseInfoEntity)).orElseGet(CollectionUtil::emptyList);
    }
}
