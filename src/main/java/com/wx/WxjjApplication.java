package com.wx;

import com.wx.dao.warehouse.mapper.extend.SkuStockDOMapperExtend;
import com.wx.dao.warehouse.model.SkuStockDetailDO;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        SpringApplication.run(WxjjApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
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
