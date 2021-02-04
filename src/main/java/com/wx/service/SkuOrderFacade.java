package com.wx.service;

import com.scy.core.CollectionUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.scy.core.model.DiffBO;
import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.scy.db.constant.DbConstant;
import com.wx.constant.CommonConstant;
import com.wx.domain.order.entity.OrderItemEntity;
import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.domain.order.service.SkuOrderDomainService;
import com.wx.domain.passport.service.UserPassportDomainService;
import com.wx.domain.sku.entity.GoodsSkuEntity;
import com.wx.domain.sku.service.GoodsSkuDomainService;
import com.wx.domain.stock.entity.SkuStockEntity;
import com.wx.domain.stock.entity.StockBaseInfoEntity;
import com.wx.domain.stock.entity.valueobject.StockTypeEnum;
import com.wx.domain.stock.service.SkuStockDomainService;
import com.wx.domain.stock.service.StockBaseInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 2:00 下午
 * ---------------------------------------
 * Desc    : 商品订单
 */
@Slf4j
@Service
public class SkuOrderFacade {

    @Autowired
    private SkuOrderDomainService skuOrderDomainService;

    @Autowired
    private UserPassportDomainService userPassportDomainService;

    @Autowired
    private GoodsSkuDomainService goodsSkuDomainService;

    @Autowired
    private StockBaseInfoDomainService stockBaseInfoDomainService;

    @Autowired
    private SkuStockDomainService skuStockDomainService;

    /**
     * 创建出库/入库订单
     */
    public long insertSkuOrder(SkuOrderEntity skuOrderEntity) {
        Optional<StockBaseInfoEntity> stockBaseInfoEntityOptional = stockBaseInfoDomainService.getStockBaseInfoEntity(skuOrderEntity.getStockBaseInfoId());
        if (!stockBaseInfoEntityOptional.isPresent()) {
            throw new BusinessException("仓库不存在");
        }

        return skuOrderDomainService.insertSkuOrder(skuOrderEntity);
    }

    /**
     * 查询订单
     */
    public Optional<SkuOrderEntity> getOrder(long orderId) {
        Optional<SkuOrderEntity> skuOrderEntityOptional = skuOrderDomainService.getOrder(orderId);
        skuOrderEntityOptional.ifPresent(skuOrderEntity -> {
            Map<Long, String> allPassportMap = userPassportDomainService.getAllPassportMap();
            skuOrderEntity.setOperatorName(allPassportMap.get(skuOrderEntity.getOperator()));
            skuOrderEntity.setStockBaseInfoName(stockBaseInfoDomainService.getStockName(skuOrderEntity.getStockBaseInfoId()));
        });
        return skuOrderEntityOptional;
    }

    /**
     * 分页查询订单
     */
    public PageResult<SkuOrderEntity> listByPage(PageParam pageParam, SkuOrderEntity skuOrderEntity) {
        PageResult<SkuOrderEntity> pageResult = skuOrderDomainService.listByPage(pageParam, skuOrderEntity);
        List<SkuOrderEntity> datas = CollectionUtil.emptyIfNull(pageResult.getDatas());

        List<Long> stockBaseInfoIds = datas.stream().map(SkuOrderEntity::getStockBaseInfoId).distinct().collect(Collectors.toList());
        Map<Long, String> stockNameMap = stockBaseInfoDomainService.getStockNameMap(stockBaseInfoIds);

        Map<Long, String> allPassportMap = userPassportDomainService.getAllPassportMap();

        datas.forEach(skuOrder -> {
            skuOrder.setOperatorName(allPassportMap.get(skuOrder.getOperator()));
            skuOrder.setStockBaseInfoName(stockNameMap.get(skuOrder.getStockBaseInfoId()));
        });
        return pageResult;
    }

    /**
     * 修改订单
     */
    public List<DiffBO> updateOrder(SkuOrderEntity skuOrderEntity) {
        Optional<StockBaseInfoEntity> stockBaseInfoEntityOptional = stockBaseInfoDomainService.getStockBaseInfoEntity(skuOrderEntity.getStockBaseInfoId());
        if (!stockBaseInfoEntityOptional.isPresent()) {
            throw new BusinessException("仓库不存在");
        }

        return skuOrderDomainService.updateOrder(skuOrderEntity);
    }

    /**
     * 查询订单所有条目
     */
    public List<OrderItemEntity> listOrderItemEntities(long orderId) {
        List<OrderItemEntity> orderItemEntities = skuOrderDomainService.listOrderItemEntities(orderId);

        List<Long> skuIds = orderItemEntities.stream().map(OrderItemEntity::getSkuId).distinct().collect(Collectors.toList());
        Map<Long, String> skuNameMap = goodsSkuDomainService.getSkuNameMap(skuIds);

        orderItemEntities.forEach(orderItemEntity -> orderItemEntity.setSkuName(skuNameMap.get(orderItemEntity.getSkuId())));
        return orderItemEntities;
    }

    /**
     * 添加订单条目
     */
    public long insertOrderItemEntity(OrderItemEntity orderItemEntity) {
        GoodsSkuEntity goodsSkuEntity = goodsSkuDomainService.getGoodsSkuEntity(orderItemEntity.getSkuId());
        if (Objects.isNull(goodsSkuEntity)) {
            throw new BusinessException(MessageUtil.format("商品不存在", "skuId", orderItemEntity.getSkuId()));
        }

        return skuOrderDomainService.insertOrderItemEntity(orderItemEntity);
    }

    /**
     * 修改订单条目
     */
    public List<DiffBO> updateOrderItemEntity(OrderItemEntity orderItemEntity) {
        return skuOrderDomainService.updateOrderItemEntity(orderItemEntity);
    }

    /**
     * 删除订单条目
     */
    public boolean deleteOrderItemEntity(OrderItemEntity orderItemEntity) {
        return skuOrderDomainService.deleteOrderItemEntity(orderItemEntity);
    }

    /**
     * 提交订单
     */
    @Transactional(value = CommonConstant.WAREHOUSE + DbConstant.TRANSACTION_MANAGER,
            propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean confirmSkuOrder(long orderId) {
        // 提交订单
        SkuOrderEntity skuOrderEntity = skuOrderDomainService.confirmSkuOrder(orderId);

        // 查询订单条目
        List<OrderItemEntity> orderItemEntities = listOrderItemEntities(orderId);
        if (CollectionUtil.isEmpty(orderItemEntities)) {
            throw new BusinessException(MessageUtil.format("订单条目不存在", "orderId", orderId));
        }

        if (Objects.equals(skuOrderEntity.getType(), StockTypeEnum.IN.getType())) {
            return inStock(skuOrderEntity, orderItemEntities);
        }

        return outStock(skuOrderEntity, orderItemEntities);
    }

    /**
     * 删除订单
     */
    public boolean deleteOrder(long orderId) {
        return skuOrderDomainService.deleteOrder(orderId);
    }

    private boolean outStock(SkuOrderEntity skuOrderEntity, List<OrderItemEntity> orderItemEntities) {
        orderItemEntities.forEach(orderItemEntity -> {
            SkuStockEntity skuStockEntity = new SkuStockEntity();
            skuStockEntity.setStockBaseInfoId(skuOrderEntity.getStockBaseInfoId());
            skuStockEntity.setSkuId(orderItemEntity.getSkuId());
            skuStockEntity.operateStock(orderItemEntity.getNumber(), skuOrderEntity.getOrderId());

            SkuStockEntity operateSkuStockResult = skuStockDomainService.reduceStock(skuStockEntity);
            log.info(MessageUtil.format("outStock",
                    "stockBaseInfoId", skuOrderEntity.getStockBaseInfoId(), "skuId", orderItemEntity.getSkuId(),
                    "operateResult", operateSkuStockResult.getStockOperateValueobject()));
        });
        return Boolean.TRUE;
    }

    private boolean inStock(SkuOrderEntity skuOrderEntity, List<OrderItemEntity> orderItemEntities) {
        orderItemEntities.forEach(orderItemEntity -> {
            SkuStockEntity skuStockEntity = new SkuStockEntity();
            skuStockEntity.setStockBaseInfoId(skuOrderEntity.getStockBaseInfoId());
            skuStockEntity.setSkuId(orderItemEntity.getSkuId());
            skuStockEntity.operateStock(orderItemEntity.getNumber(), skuOrderEntity.getOrderId());

            SkuStockEntity operateSkuStockResult = skuStockDomainService.addStock(skuStockEntity);
            log.info(MessageUtil.format("inStock",
                    "stockBaseInfoId", skuOrderEntity.getStockBaseInfoId(), "skuId", orderItemEntity.getSkuId(),
                    "operateResult", operateSkuStockResult.getStockOperateValueobject()));
        });
        return Boolean.TRUE;
    }
}
