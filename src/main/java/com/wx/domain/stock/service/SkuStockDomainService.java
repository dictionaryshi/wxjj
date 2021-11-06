package com.wx.domain.stock.service;

import com.scy.core.CollectionUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.scy.db.util.ForceMasterHelper;
import com.scy.redis.lock.RedisLock;
import com.wx.dao.warehouse.mapper.SkuStockDOMapper;
import com.wx.dao.warehouse.mapper.SkuStockDetailDOMapper;
import com.wx.dao.warehouse.mapper.extend.SkuStockDOMapperExtend;
import com.wx.dao.warehouse.model.SkuStockDO;
import com.wx.dao.warehouse.model.SkuStockDOExample;
import com.wx.dao.warehouse.model.SkuStockDetailDO;
import com.wx.dao.warehouse.model.SkuStockDetailDOExample;
import com.wx.domain.stock.entity.SkuStockDetailEntity;
import com.wx.domain.stock.entity.SkuStockEntity;
import com.wx.domain.stock.entity.valueobject.StockTypeEnum;
import com.wx.domain.stock.factory.SkuStockFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2021/1/29
 * Time    : 8:21 下午
 * ---------------------------------------
 * Desc    : 商品库存领域服务
 */
@Slf4j
@Service
public class SkuStockDomainService {

    @Autowired
    private SkuStockDOMapper skuStockDOMapper;

    @Autowired
    private SkuStockDOMapperExtend skuStockDOMapperExtend;

    @Autowired
    private SkuStockDetailDOMapper skuStockDetailDOMapper;

    @Autowired
    private RedisLock redisLock;

    public long insert(SkuStockEntity skuStockEntity) {
        Optional<SkuStockDO> skuStockOptional = SkuStockFactory.toSkuStockDO(skuStockEntity);
        if (!skuStockOptional.isPresent()) {
            throw new BusinessException("库存实体不存在");
        }

        SkuStockDO skuStockDO = skuStockOptional.get();
        skuStockDOMapper.insertSelective(skuStockDO);
        return skuStockDO.getId();
    }

    public Optional<SkuStockEntity> getSkuStockEntity(long stockBaseInfoId, long skuId) {
        SkuStockDOExample skuStockDOExample = new SkuStockDOExample();
        SkuStockDOExample.Criteria criteria = skuStockDOExample.createCriteria();
        criteria.andStockBaseInfoIdEqualTo(stockBaseInfoId);
        criteria.andSkuIdEqualTo(skuId);
        List<SkuStockDO> skuStocks = skuStockDOMapper.selectByExample(skuStockDOExample);
        SkuStockDO skuStockDO = CollectionUtil.firstElement(skuStocks);
        return SkuStockFactory.toSkuStockEntity(skuStockDO);
    }

    public Optional<Long> getSkuStock(long stockBaseInfoId, long skuId) {
        Optional<SkuStockEntity> skuStockEntityOptional = getSkuStockEntity(stockBaseInfoId, skuId);
        return skuStockEntityOptional.map(SkuStockEntity::getStock);
    }

    public SkuStockEntity updateStock(SkuStockEntity skuStockEntity) {
        String lockKey = skuStockEntity.getLockKey();
        try {
            redisLock.lock(lockKey);
            Optional<SkuStockEntity> skuStockEntityOptional = getSkuStockEntity(skuStockEntity.getStockBaseInfoId(), skuStockEntity.getSkuId());
            if (!skuStockEntityOptional.isPresent()) {
                throw new BusinessException(MessageUtil.format("库存不存在",
                        "stockBaseInfoId", skuStockEntity.getStockBaseInfoId(), "skuId", skuStockEntity.getSkuId()));
            }

            SkuStockEntity skuStockEntityForUpdate = new SkuStockEntity();
            skuStockEntityForUpdate.setId(skuStockEntityOptional.get().getId());
            skuStockEntityForUpdate.setStock(skuStockEntity.getStock());
            Optional<SkuStockDO> skuStockOptional = SkuStockFactory.toSkuStockDO(skuStockEntityForUpdate);
            skuStockOptional.ifPresent(skuStockDO -> skuStockDOMapper.updateByPrimaryKeySelective(skuStockDO));

            skuStockEntity.operateStock(skuStockEntity.getStock(), null);
            operateStockAfter(skuStockEntity, skuStockEntityOptional.get().getStock());

            insertStockDetail(skuStockEntity, StockTypeEnum.INIT);
            return skuStockEntity;
        } finally {
            redisLock.unlock(lockKey);
        }
    }

    public SkuStockEntity addStock(SkuStockEntity skuStockEntity) {
        String lockKey = skuStockEntity.getLockKey();
        try {
            redisLock.lock(lockKey);
            Optional<SkuStockEntity> skuStockEntityOptional = getSkuStockEntity(skuStockEntity.getStockBaseInfoId(), skuStockEntity.getSkuId());
            if (!skuStockEntityOptional.isPresent()) {
                SkuStockEntity skuStockEntityForInsert = new SkuStockEntity();
                skuStockEntityForInsert.setStockBaseInfoId(skuStockEntity.getStockBaseInfoId());
                skuStockEntityForInsert.setSkuId(skuStockEntity.getSkuId());
                skuStockEntityForInsert.setStock(skuStockEntity.getStockOperateValueobject().getStockOffset());
                insert(skuStockEntityForInsert);

                operateStockAfter(skuStockEntity, null);

                insertStockDetail(skuStockEntity, StockTypeEnum.IN);
                return skuStockEntity;
            }

            skuStockDOMapperExtend.addStock(skuStockEntityOptional.get().getId(), skuStockEntity.getStockOperateValueobject().getStockOffset());
            operateStockAfter(skuStockEntity, skuStockEntityOptional.get().getStock());

            insertStockDetail(skuStockEntity, StockTypeEnum.IN);
            return skuStockEntity;
        } finally {
            redisLock.unlock(lockKey);
        }
    }

    public SkuStockEntity reduceStock(SkuStockEntity skuStockEntity) {
        String lockKey = skuStockEntity.getLockKey();
        try {
            redisLock.lock(lockKey);
            Optional<SkuStockEntity> skuStockEntityOptional = getSkuStockEntity(skuStockEntity.getStockBaseInfoId(), skuStockEntity.getSkuId());
            if (!skuStockEntityOptional.isPresent()) {
                throw new BusinessException(MessageUtil.format("库存不存在",
                        "stockBaseInfoId", skuStockEntity.getStockBaseInfoId(), "skuId", skuStockEntity.getSkuId()));
            }

            long dbStock = skuStockEntityOptional.get().getStock();
            if (dbStock < skuStockEntity.getStockOperateValueobject().getStockOffset()) {
                throw new BusinessException(MessageUtil.format("库存不足",
                        "stockBaseInfoId", skuStockEntity.getStockBaseInfoId(), "skuId", skuStockEntity.getSkuId()));
            }

            skuStockDOMapperExtend.reduceStock(skuStockEntityOptional.get().getId(), skuStockEntity.getStockOperateValueobject().getStockOffset());
            operateStockAfter(skuStockEntity, skuStockEntityOptional.get().getStock());

            insertStockDetail(skuStockEntity, StockTypeEnum.OUT);
            return skuStockEntity;
        } finally {
            redisLock.unlock(lockKey);
        }
    }

    public PageResult<SkuStockEntity> listByPage(PageParam pageParam, SkuStockEntity skuStockEntity) {
        PageResult<SkuStockEntity> pageResult = new PageResult<>();
        pageResult.setPage(pageParam.getPage());
        pageResult.setLimit(pageParam.getLimit());

        SkuStockDOExample skuStockDOExample = new SkuStockDOExample();
        skuStockDOExample.setOrderByClause("id desc");
        skuStockDOExample.setOffset(pageParam.getOffset());
        skuStockDOExample.setLimit(pageParam.getLimit());

        SkuStockDOExample.Criteria criteria = skuStockDOExample.createCriteria();
        criteria.andStockBaseInfoIdEqualTo(skuStockEntity.getStockBaseInfoId());

        if (!CollectionUtil.isEmpty(skuStockEntity.getSkuIds())) {
            criteria.andSkuIdIn(skuStockEntity.getSkuIds());
        }

        pageResult.setTotal((int) skuStockDOMapper.countByExample(skuStockDOExample));

        List<SkuStockDO> skuStocks = skuStockDOMapper.selectByExample(skuStockDOExample);
        List<SkuStockEntity> datas = CollectionUtil.map(skuStocks, skuStock -> SkuStockFactory.toSkuStockEntity(skuStock).orElse(null))
                .collect(Collectors.toList());
        pageResult.setDatas(datas);
        return pageResult;
    }

    public PageResult<SkuStockDetailEntity> listStockDetailByPage(PageParam pageParam, SkuStockDetailEntity skuStockDetailEntity) {
        PageResult<SkuStockDetailEntity> pageResult = new PageResult<>();
        pageResult.setPage(pageParam.getPage());
        pageResult.setLimit(pageParam.getLimit());

        SkuStockDetailDOExample skuStockDetailDOExample = new SkuStockDetailDOExample();
        skuStockDetailDOExample.setOrderByClause("id desc");
        skuStockDetailDOExample.setOffset(pageParam.getOffset());
        skuStockDetailDOExample.setLimit(pageParam.getLimit());

        SkuStockDetailDOExample.Criteria criteria = skuStockDetailDOExample.createCriteria();
        criteria.andStockBaseInfoIdEqualTo(skuStockDetailEntity.getStockBaseInfoId());

        if (!CollectionUtil.isEmpty(skuStockDetailEntity.getSkuIds())) {
            criteria.andSkuIdIn(skuStockDetailEntity.getSkuIds());
        }

        if (!Objects.isNull(skuStockDetailEntity.getOrderId())) {
            criteria.andOrderIdEqualTo(skuStockDetailEntity.getOrderId());
        }

        if (!Objects.isNull(skuStockDetailEntity.getCreatedAtStart())) {
            criteria.andCreatedAtGreaterThanOrEqualTo(skuStockDetailEntity.getCreatedAtStart());
        }

        if (!Objects.isNull(skuStockDetailEntity.getCreatedAtEnd())) {
            criteria.andCreatedAtLessThanOrEqualTo(skuStockDetailEntity.getCreatedAtEnd());
        }

        pageResult.setTotal((int) skuStockDetailDOMapper.countByExample(skuStockDetailDOExample));

        List<SkuStockDetailDO> skuStockDetails = skuStockDetailDOMapper.selectByExample(skuStockDetailDOExample);
        List<SkuStockDetailEntity> datas = CollectionUtil.map(skuStockDetails, SkuStockFactory::toSkuStockDetailEntity)
                .collect(Collectors.toList());
        pageResult.setDatas(datas);
        return pageResult;
    }

    private void operateStockAfter(SkuStockEntity skuStockEntity, Long stockBefore) {
        try {
            ForceMasterHelper.forceMaster();
            skuStockEntity.operateStockAfter(stockBefore,
                    getSkuStock(skuStockEntity.getStockBaseInfoId(), skuStockEntity.getSkuId()).orElse(null));
        } finally {
            ForceMasterHelper.clearForceMaster();
        }
    }

    private void insertStockDetail(SkuStockEntity skuStockEntity, StockTypeEnum stockTypeEnum) {
        SkuStockDetailDO skuStockDetailDO = SkuStockFactory.toSkuStockDetailDO(skuStockEntity, stockTypeEnum);
        skuStockDetailDOMapper.insertSelective(skuStockDetailDO);
    }
}
