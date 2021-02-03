package com.wx.service;

import com.scy.core.CollectionUtil;
import com.scy.core.model.DiffBO;
import com.scy.core.page.PageParam;
import com.scy.core.page.PageResult;
import com.wx.domain.order.entity.SkuOrderEntity;
import com.wx.domain.order.service.SkuOrderDomainService;
import com.wx.domain.passport.service.UserPassportDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 2:00 下午
 * ---------------------------------------
 * Desc    : 商品订单
 */
@Service
public class SkuOrderFacade {

    @Autowired
    private SkuOrderDomainService skuOrderDomainService;

    @Autowired
    private UserPassportDomainService userPassportDomainService;

    /**
     * 创建出库/入库订单
     */
    public long insertSkuOrder(SkuOrderEntity skuOrderEntity) {
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
        });
        return skuOrderEntityOptional;
    }

    /**
     * 分页查询订单
     */
    public PageResult<SkuOrderEntity> listByPage(PageParam pageParam, SkuOrderEntity skuOrderEntity) {
        Map<Long, String> allPassportMap = userPassportDomainService.getAllPassportMap();

        PageResult<SkuOrderEntity> pageResult = skuOrderDomainService.listByPage(pageParam, skuOrderEntity);
        List<SkuOrderEntity> datas = CollectionUtil.emptyIfNull(pageResult.getDatas());
        datas.forEach(skuOrder -> skuOrder.setOperatorName(allPassportMap.get(skuOrder.getOperator())));
        return pageResult;
    }

    /**
     * 修改订单
     */
    public List<DiffBO> updateOrder(SkuOrderEntity skuOrderEntity) {
        return skuOrderDomainService.updateOrder(skuOrderEntity);
    }
}
