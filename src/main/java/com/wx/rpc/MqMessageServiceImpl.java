package com.wx.rpc;

import com.scy.core.StringUtil;
import com.scy.core.rest.ResponseResult;
import com.scy.netty.mq.MqMessage;
import com.scy.netty.mq.MqMessageService;
import com.scy.netty.rpc.provider.annotation.RpcService;
import com.wx.dao.warehouse.mapper.MqMessageDOMapper;
import com.wx.dao.warehouse.model.MqMessageDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private MqMessageDOMapper mqMessageMapper;

    @Override
    public ResponseResult<Long> push(MqMessage mqMessage) {
        MqMessageDO mqMessageDO = new MqMessageDO();
        mqMessageDO.setTopic(mqMessage.getTopic());
        mqMessageDO.setMqGroup(mqMessage.getGroup());
        mqMessageDO.setStatus(0);
        mqMessageDO.setRetryCount(mqMessage.getRetryCount());
        mqMessageDO.setShardingId(mqMessage.getShardingId());
        mqMessageDO.setTimeout(mqMessage.getTimeout());
        mqMessageDO.setEffectTime(mqMessage.getEffectTime());
        mqMessageDO.setData(mqMessage.getData());
        mqMessageDO.setLog(StringUtil.EMPTY);
        mqMessageMapper.insertSelective(mqMessageDO);
        return ResponseResult.success(mqMessageDO.getId());
    }
}
