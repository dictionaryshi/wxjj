package com.wx.rpc;

import com.scy.core.CollectionUtil;
import com.scy.core.StringUtil;
import com.scy.core.format.NumberUtil;
import com.scy.core.rest.ResponseResult;
import com.scy.netty.mq.MessageStatusEnum;
import com.scy.netty.mq.MqMessage;
import com.scy.netty.mq.MqMessageService;
import com.scy.netty.rpc.provider.annotation.RpcService;
import com.scy.zookeeper.ZkClient;
import com.wx.dao.warehouse.mapper.MqMessageDOMapper;
import com.wx.dao.warehouse.mapper.extend.MqMessageDOMapperExt;
import com.wx.dao.warehouse.model.MqMessageDO;
import com.wx.dao.warehouse.model.MqMessageDOExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    @Autowired
    private MqMessageDOMapper mqMessageDOMapper;

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

    @Override
    public ResponseResult<List<MqMessage>> pull(String topic, String group, int consumerRank, int consumerTotal) {
        MqMessageDOExample mqMessageExample = new MqMessageDOExample();
        mqMessageExample.setOrderByClause("id asc");
        mqMessageExample.setOffset(0);
        mqMessageExample.setLimit(200);

        MqMessageDOExample.Criteria criteria = mqMessageExample.createCriteria();
        criteria.andTopicEqualTo(topic);
        criteria.andMqGroupEqualTo(group);
        criteria.andStatusEqualTo(MessageStatusEnum.NEW.getStatus());
        criteria.andEffectTimeLessThanOrEqualTo(System.currentTimeMillis());

        List<MqMessageDO> mqMessages = mqMessageDOMapper.selectByExampleWithBLOBs(mqMessageExample);
        if (consumerTotal <= 1) {
            List<MqMessage> messages = mqMessages.stream().map(this::toMqMessage).collect(Collectors.toList());
            return ResponseResult.success(messages);
        }

        List<MqMessage> messages = mqMessages.stream()
                .filter(mqMessageDO -> {
                    if (mqMessageDO.getShardingId() > 0) {
                        return Objects.equals(NumberUtil.modulo(mqMessageDO.getShardingId(), consumerTotal), consumerRank);
                    }

                    return Objects.equals(NumberUtil.modulo(mqMessageDO.getId(), consumerTotal), consumerRank);
                })
                .map(this::toMqMessage).collect(Collectors.toList());
        return ResponseResult.success(messages);
    }

    @Override
    public ResponseResult<Integer> lockMessage(long id, String appendLog) {
        return ResponseResult.success(mqMessageDOMapperExt.lockMessage(id, StringUtil.BR + appendLog, MessageStatusEnum.NEW.getStatus(), MessageStatusEnum.RUNNING.getStatus()));
    }

    @Override
    public ResponseResult<Integer> callbackMessage(long id, int status, String appendLog) {
        return ResponseResult.success(mqMessageDOMapperExt.callbackMessage(id, StringUtil.BR + appendLog, status));
    }

    private MqMessage toMqMessage(MqMessageDO mqMessageDO) {
        MqMessage mqMessage = new MqMessage();
        mqMessage.setId(mqMessageDO.getId());
        mqMessage.setTopic(mqMessageDO.getTopic());
        mqMessage.setGroup(mqMessageDO.getMqGroup());
        mqMessage.setStatus(mqMessageDO.getStatus());
        mqMessage.setRetryCount(mqMessageDO.getRetryCount());
        mqMessage.setShardingId(mqMessageDO.getShardingId());
        mqMessage.setTimeout(mqMessageDO.getTimeout());
        mqMessage.setEffectTime(mqMessageDO.getEffectTime());
        mqMessage.setCreatedAt(mqMessageDO.getCreatedAt());
        mqMessage.setUpdatedAt(mqMessageDO.getUpdatedAt());
        mqMessage.setData(mqMessageDO.getData());
        mqMessage.setLog(mqMessageDO.getLog());
        return mqMessage;
    }
}
