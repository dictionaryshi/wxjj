package com.wx.domain.order.service;

import com.scy.core.CollectionUtil;
import com.scy.core.DiffUtil;
import com.scy.core.StringUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.scy.core.model.DiffBO;
import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.scy.db.util.ForceMasterHelper;
import com.scy.redis.lock.RedisLock;
import com.wx.dao.warehouse.mapper.OrderItemDOMapper;
import com.wx.dao.warehouse.mapper.extend.SkuOrderDOMapperExtend;
import com.wx.dao.warehouse.model.OrderItemDO;
import com.wx.dao.warehouse.model.OrderItemDOExample;
import com.wx.dao.warehouse.model.SkuOrderDO;
import com.wx.dao.warehouse.model.SkuOrderDOExample;
import com.wx.dao.warehouse.model.extend.SkuOrderDOExampleExtend;
import com.wx.domain.order.entity.OrderItemEntity;
import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.domain.order.entity.valueobject.OrderStatusEnum;
import com.wx.domain.order.factory.SkuOrderFactory;
import com.wx.infrastructure.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 11:52 上午
 * ---------------------------------------
 * Desc    : 商品订单领域服务
 */
@Service
public class SkuOrderDomainService {

    @Autowired
    private SkuOrderDOMapperExtend skuOrderDOMapper;

    @Autowired
    private OrderItemDOMapper orderItemDOMapper;

    @Autowired
    private IdService idService;

    @Autowired
    private RedisLock redisLock;

    public long insertSkuOrder(SkuOrderEntity skuOrderEntity) {
        skuOrderEntity.setOrderId(idService.getId());
        skuOrderEntity.waitConfirm();

        SkuOrderDO skuOrderDO = SkuOrderFactory.toSkuOrderDO(skuOrderEntity);
        skuOrderDOMapper.insertSelective(skuOrderDO);
        return skuOrderDO.getOrderId();
    }

    public Optional<SkuOrderEntity> getOrder(long orderId) {
        SkuOrderDOExample skuOrderDOExample = new SkuOrderDOExample();
        SkuOrderDOExample.Criteria criteria = skuOrderDOExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);

        List<SkuOrderDO> skuOrders = skuOrderDOMapper.selectByExample(skuOrderDOExample);
        SkuOrderDO skuOrderDO = CollectionUtil.firstElement(skuOrders);
        return Optional.ofNullable(skuOrderDO).flatMap(SkuOrderFactory::toSkuOrderEntity);
    }

    public PageResult<SkuOrderEntity> listByPage(PageParam pageParam, SkuOrderEntity skuOrderEntity) {
        PageResult<SkuOrderEntity> pageResult = new PageResult<>();
        pageResult.setPage(pageParam.getPage());
        pageResult.setLimit(pageParam.getLimit());

        SkuOrderDOExampleExtend skuOrderDOExampleExtend = new SkuOrderDOExampleExtend();
        pageParam.setOrderField("id");
        pageParam.setDesc(Boolean.TRUE);
        skuOrderDOExampleExtend.setPageParam(pageParam);

        SkuOrderDOExample.Criteria criteria = skuOrderDOExampleExtend.createCriteria();
        criteria.andTypeEqualTo(skuOrderEntity.getType());

        if (!Objects.isNull(skuOrderEntity.getOrderId())) {
            criteria.andOrderIdEqualTo(skuOrderEntity.getOrderId());
        }

        if (!Objects.isNull(skuOrderEntity.getCreatedAtStart())) {
            criteria.andCreatedAtGreaterThanOrEqualTo(skuOrderEntity.getCreatedAtStart());
        }

        if (!Objects.isNull(skuOrderEntity.getCreatedAtEnd())) {
            criteria.andCreatedAtLessThanOrEqualTo(skuOrderEntity.getCreatedAtEnd());
        }

        if (!Objects.isNull(skuOrderEntity.getStatus())) {
            criteria.andStatusEqualTo(skuOrderEntity.getStatus());
        }

        if (!StringUtil.isEmpty(skuOrderEntity.getCustomerPhone())) {
            criteria.andCustomerPhoneLike(skuOrderEntity.getCustomerPhone() + StringUtil.PERCENT);
        }

        pageResult.setTotal((int) skuOrderDOMapper.countByExample(skuOrderDOExampleExtend));

        List<SkuOrderDO> skuOrders = skuOrderDOMapper.selectByPage(skuOrderDOExampleExtend);
        List<SkuOrderEntity> datas = CollectionUtil.map(skuOrders, skuOrder -> SkuOrderFactory.toSkuOrderEntity(skuOrder).orElse(null))
                .collect(Collectors.toList());
        pageResult.setDatas(datas);
        return pageResult;
    }

    public List<DiffBO> updateOrder(SkuOrderEntity skuOrderEntity) {
        Optional<SkuOrderEntity> skuOrderEntityOptional = getOrder(skuOrderEntity.getOrderId());
        if (!skuOrderEntityOptional.isPresent()) {
            throw new BusinessException(MessageUtil.format("订单不存在", "orderId", skuOrderEntity.getOrderId()));
        }

        String lockKey = skuOrderEntity.getLockKey();
        try {
            redisLock.lock(lockKey);

            int status = skuOrderEntityOptional.get().getStatus();
            if (!Objects.equals(status, OrderStatusEnum.WAIT_TO_CONFIRMED.getStatus())) {
                throw new BusinessException(MessageUtil.format("订单已确认不能修改", "orderId", skuOrderEntity.getOrderId()));
            }

            SkuOrderDO skuOrderDO = new SkuOrderDO();
            skuOrderDO.setOperator(skuOrderEntity.getOperator());
            skuOrderDO.setPrice(skuOrderEntity.getPrice());
            skuOrderDO.setCustomerName(skuOrderEntity.getCustomerName());
            skuOrderDO.setCustomerPhone(skuOrderEntity.getCustomerPhone());
            skuOrderDO.setCustomerAddress(skuOrderEntity.getCustomerAddress());
            skuOrderDO.setRemark(skuOrderEntity.getRemark());

            SkuOrderDOExample skuOrderDOExample = new SkuOrderDOExample();
            SkuOrderDOExample.Criteria criteria = skuOrderDOExample.createCriteria();
            criteria.andOrderIdEqualTo(skuOrderEntity.getOrderId());
            skuOrderDOMapper.updateByExampleSelective(skuOrderDO, skuOrderDOExample);

            ForceMasterHelper.forceMaster();
            Optional<SkuOrderEntity> afterSkuOrderEntityOptional = getOrder(skuOrderEntity.getOrderId());
            return afterSkuOrderEntityOptional.map(afterSkuOrderEntity -> DiffUtil.diff(skuOrderEntityOptional.get(), afterSkuOrderEntity)).orElse(Collections.emptyList());
        } finally {
            ForceMasterHelper.clearForceMaster();
            redisLock.unlock(lockKey);
        }
    }

    public Optional<OrderItemEntity> getOrderItemEntity(long orderId, long skuId) {
        OrderItemDOExample orderItemDOExample = new OrderItemDOExample();
        OrderItemDOExample.Criteria criteria = orderItemDOExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andSkuIdEqualTo(skuId);

        List<OrderItemDO> orderItems = orderItemDOMapper.selectByExample(orderItemDOExample);
        OrderItemDO orderItemDO = CollectionUtil.firstElement(orderItems);
        return SkuOrderFactory.toOrderItemEntity(orderItemDO);
    }

    public List<OrderItemEntity> listOrderItemEntities(long orderId) {
        OrderItemDOExample orderItemDOExample = new OrderItemDOExample();
        OrderItemDOExample.Criteria criteria = orderItemDOExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);

        List<OrderItemDO> orderItems = orderItemDOMapper.selectByExample(orderItemDOExample);
        return CollectionUtil.map(orderItems, orderItemDO -> SkuOrderFactory.toOrderItemEntity(orderItemDO).orElse(null)).collect(Collectors.toList());
    }

    public long insertOrderItemEntity(OrderItemEntity orderItemEntity) {
        if (orderItemEntity.getNumber() <= 0) {
            throw new BusinessException(MessageUtil.format("商品数量必须大于0", "number", orderItemEntity.getNumber()));
        }

        String lockKey = orderItemEntity.getLockKey();
        try {
            redisLock.lock(lockKey);
            Optional<SkuOrderEntity> orderEntityOptional = getOrder(orderItemEntity.getOrderId());
            if (!orderEntityOptional.isPresent()) {
                throw new BusinessException(MessageUtil.format("订单不存在", "orderId", orderItemEntity.getOrderId()));
            }

            int status = orderEntityOptional.get().getStatus();
            if (!Objects.equals(status, OrderStatusEnum.WAIT_TO_CONFIRMED.getStatus())) {
                throw new BusinessException(MessageUtil.format("订单已确认不能添加订单条目", "orderId", orderItemEntity.getOrderId()));
            }

            Optional<OrderItemEntity> orderItemEntityOptional = getOrderItemEntity(orderItemEntity.getOrderId(), orderItemEntity.getSkuId());
            if (orderItemEntityOptional.isPresent()) {
                throw new BusinessException(MessageUtil.format("订单条目已存在"));
            }

            OrderItemDO orderItemDO = SkuOrderFactory.toOrderItemDO(orderItemEntity);
            orderItemDOMapper.insertSelective(orderItemDO);
            return orderItemDO.getId();
        } finally {
            redisLock.unlock(lockKey);
        }
    }

    public List<DiffBO> updateOrderItemEntity(OrderItemEntity orderItemEntity) {
        if (orderItemEntity.getNumber() <= 0) {
            throw new BusinessException(MessageUtil.format("商品数量必须大于0", "number", orderItemEntity.getNumber()));
        }

        String lockKey = orderItemEntity.getLockKey();
        try {
            redisLock.lock(lockKey);
            Optional<SkuOrderEntity> orderEntityOptional = getOrder(orderItemEntity.getOrderId());
            if (!orderEntityOptional.isPresent()) {
                throw new BusinessException(MessageUtil.format("订单不存在", "orderId", orderItemEntity.getOrderId()));
            }

            int status = orderEntityOptional.get().getStatus();
            if (!Objects.equals(status, OrderStatusEnum.WAIT_TO_CONFIRMED.getStatus())) {
                throw new BusinessException(MessageUtil.format("订单已确认不能修改订单条目", "orderId", orderItemEntity.getOrderId()));
            }

            Optional<OrderItemEntity> orderItemEntityOptional = getOrderItemEntity(orderItemEntity.getOrderId(), orderItemEntity.getSkuId());
            if (!orderItemEntityOptional.isPresent()) {
                throw new BusinessException(MessageUtil.format("订单条目不存在"));
            }

            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setId(orderItemEntityOptional.get().getId());
            orderItemDO.setNumber(orderItemEntity.getNumber());
            orderItemDOMapper.updateByPrimaryKeySelective(orderItemDO);

            ForceMasterHelper.forceMaster();
            Optional<OrderItemEntity> afterOrderItemEntityOptional = getOrderItemEntity(orderItemEntity.getOrderId(), orderItemEntity.getSkuId());
            return afterOrderItemEntityOptional.map(afterOrderItemEntity -> DiffUtil.diff(orderItemEntityOptional.get(), afterOrderItemEntity)).orElse(Collections.emptyList());
        } finally {
            ForceMasterHelper.clearForceMaster();
            redisLock.unlock(lockKey);
        }
    }

    public boolean deleteOrderItemEntity(OrderItemEntity orderItemEntity) {
        String lockKey = orderItemEntity.getLockKey();
        try {
            redisLock.lock(lockKey);
            Optional<SkuOrderEntity> orderEntityOptional = getOrder(orderItemEntity.getOrderId());
            if (!orderEntityOptional.isPresent()) {
                throw new BusinessException(MessageUtil.format("订单不存在", "orderId", orderItemEntity.getOrderId()));
            }

            int status = orderEntityOptional.get().getStatus();
            if (!Objects.equals(status, OrderStatusEnum.WAIT_TO_CONFIRMED.getStatus())) {
                throw new BusinessException(MessageUtil.format("订单已确认不能删除订单条目", "orderId", orderItemEntity.getOrderId()));
            }

            Optional<OrderItemEntity> orderItemEntityOptional = getOrderItemEntity(orderItemEntity.getOrderId(), orderItemEntity.getSkuId());
            if (!orderItemEntityOptional.isPresent()) {
                throw new BusinessException(MessageUtil.format("订单条目不存在"));
            }

            return orderItemDOMapper.deleteByPrimaryKey(orderItemEntityOptional.get().getId()) == 1;
        } finally {
            redisLock.unlock(lockKey);
        }
    }

    public SkuOrderEntity confirmSkuOrder(long orderId) {
        SkuOrderEntity skuOrderEntity = new SkuOrderEntity();
        skuOrderEntity.setOrderId(orderId);
        skuOrderEntity.confirm();

        String lockKey = skuOrderEntity.getLockKey();
        try {
            redisLock.lock(lockKey);
            Optional<SkuOrderEntity> orderEntityOptional = getOrder(orderId);
            if (!orderEntityOptional.isPresent()) {
                throw new BusinessException(MessageUtil.format("订单不存在", "orderId", orderId));
            }

            int status = orderEntityOptional.get().getStatus();
            if (!Objects.equals(status, OrderStatusEnum.WAIT_TO_CONFIRMED.getStatus())) {
                throw new BusinessException(MessageUtil.format("订单已确认", "orderId", orderId));
            }

            SkuOrderDO skuOrderDO = SkuOrderFactory.toSkuOrderDO(skuOrderEntity);

            SkuOrderDOExample skuOrderDOExample = new SkuOrderDOExample();
            SkuOrderDOExample.Criteria criteria = skuOrderDOExample.createCriteria();
            criteria.andOrderIdEqualTo(orderId);
            skuOrderDOMapper.updateByExampleSelective(skuOrderDO, skuOrderDOExample);

            ForceMasterHelper.forceMaster();
            return getOrder(orderId).get();
        } finally {
            ForceMasterHelper.clearForceMaster();
            redisLock.unlock(lockKey);
        }
    }
}
