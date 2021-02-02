package com.wx.domain.order.service;

import com.scy.core.CollectionUtil;
import com.scy.core.StringUtil;
import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.wx.dao.warehouse.mapper.extend.SkuOrderDOMapperExtend;
import com.wx.dao.warehouse.model.SkuOrderDO;
import com.wx.dao.warehouse.model.SkuOrderDOExample;
import com.wx.dao.warehouse.model.extend.SkuOrderDOExampleExtend;
import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.domain.order.factory.SkuOrderFactory;
import com.wx.infrastructure.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private IdService idService;

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
}
