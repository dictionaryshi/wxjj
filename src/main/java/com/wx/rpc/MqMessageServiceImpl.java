package com.wx.rpc;

import com.scy.core.CollectionUtil;
import com.scy.core.StringUtil;
import com.scy.core.rest.ResponseResult;
import com.scy.netty.mq.MessageStatusEnum;
import com.scy.netty.mq.MqMessage;
import com.scy.netty.mq.MqMessageService;
import com.scy.netty.rpc.provider.annotation.RpcService;
import com.scy.zookeeper.ZkClient;
import com.wx.dao.warehouse.mapper.extend.MqMessageDOMapperExt;
import com.wx.dao.warehouse.model.MqMessageDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2022/7/27
 * Time    : 2:55 下午
 * ---------------------------------------
 * Desc    : MqMessageService
 */
@Slf4j
@RpcService(version = "1.0", corePoolSize = 10, maximumPoolSize = 30, queueSize = 500)
@Service
public class MqMessageServiceImpl implements MqMessageService {

    @Autowired
    private ZkClient zkClient;

    @Autowired
    private MqMessageDOMapperExt mqMessageDOMapperExt;

    @Override
    public ResponseResult<Long> push(MqMessage mqMessage) {
        if (StringUtil.isEmpty(mqMessage.getTopic())) {
            return ResponseResult.success(null);
        }

        List<String> addresses = zkClient.getChildren("/mq/".concat(mqMessage.getTopic()));
        if (CollectionUtil.isEmpty(addresses)) {
            return ResponseResult.success(null);
        }

        List<MqMessageDO> mqMessages = addresses.stream()
                .map(address -> address.substring(0, address.lastIndexOf("_")))
                .distinct()
                .map(group -> {
                    MqMessageDO mqMessageDO = new MqMessageDO();
                    mqMessageDO.setTopic(mqMessage.getTopic());
                    mqMessageDO.setMqGroup(group);
                    mqMessageDO.setStatus(MessageStatusEnum.NEW.getStatus());
                    mqMessageDO.setRetryCount(mqMessage.getRetryCount());
                    mqMessageDO.setShardingId(mqMessage.getShardingId());
                    mqMessageDO.setTimeout(mqMessage.getTimeout());
                    mqMessageDO.setEffectTime(mqMessage.getEffectTime());
                    mqMessageDO.setData(mqMessage.getData());
                    mqMessageDO.setLog(mqMessage.getLog());
                    return mqMessageDO;
                }).collect(Collectors.toList());

        int i = mqMessageDOMapperExt.batchInsert(mqMessages);
        return ResponseResult.success(Long.parseLong(String.valueOf(i)));
    }
}
