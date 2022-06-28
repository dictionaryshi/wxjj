package com.wx;

import com.scy.core.reflect.ReflectionsUtil;
import com.scy.core.rest.ResponseResult;
import com.scy.netty.rpc.consumer.RpcReference;
import com.scy.netty.util.NettyUtil;
import com.wx.dao.warehouse.mapper.extend.SkuStockDOMapperExtend;
import com.wx.dao.warehouse.model.SkuStockDetailDO;
import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.domain.passport.entity.UserPassportEntity;
import com.wx.netty.service.RpcModel;
import com.wx.netty.service.UserService;
import io.netty.util.internal.PlatformDependent;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * WxjjApplication
 *
 * @author shichunyang
 */
@RestController
@EnableTransactionManagement
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        MybatisAutoConfiguration.class,
        KafkaAutoConfiguration.class,
})
public class WxjjApplication {

    @Autowired
    private SkuStockDOMapperExtend skuStockDOMapperExtend;

    @RpcReference(version = "1.0", timeout = 6000)
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(WxjjApplication.class, args);
    }

    @GetMapping("/hello")
    public ResponseResult<RpcModel> hello() throws InterruptedException, ExecutionException, TimeoutException {
        RpcModel rpcModel = new RpcModel();
        rpcModel.setUserId(1L);
        rpcModel.setPassport("chunyang");
        rpcModel.setPassword("naodian123");
        rpcModel.setCreatedAt(new Date());

        System.out.println(NettyUtil.usedDirectMemory());
        System.out.println(NettyUtil.maxDirectMemory());

        Future<ResponseResult<RpcModel>> responseResultFuture = userService.getRpcModel(rpcModel).getResponseResultFuture();
        ResponseResult<RpcModel> responseResult = responseResultFuture.get(500, TimeUnit.MILLISECONDS);
        rpcModel = responseResult.getData();
        return ResponseResult.success(rpcModel);
    }

    @GetMapping("/batch")
    public String batch() {
        List<SkuStockDetailDO> skuStockDetails = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SkuStockDetailDO skuStockDetailDO = new SkuStockDetailDO();
            skuStockDetailDO.setStockBaseInfoId((long) i);
            skuStockDetailDO.setSkuId((long) i + 1);
            skuStockDetailDO.setType(i + 2);
            skuStockDetailDO.setStockOffset((long) i + 3);
            skuStockDetailDO.setStockBefore((long) i + 4);
            skuStockDetailDO.setStockAfter((long) i + 5);
            skuStockDetailDO.setOrderId((long) i + 6);
            skuStockDetailDO.setOperator((long) i + 7);
            skuStockDetailDO.setRemark((i + 8) + "");
            skuStockDetails.add(skuStockDetailDO);
        }
        skuStockDOMapperExtend.batchInsert(skuStockDetails);
        return "done";
    }
}
