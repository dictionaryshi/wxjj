package com.wx.netty.service.impl;

import com.scy.core.rest.ResponseResult;
import com.scy.netty.rpc.provider.annotation.RpcService;
import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.domain.passport.entity.UserPassportEntity;
import com.wx.netty.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : shichunyang
 * Date    : 2022/2/18
 * Time    : 3:25 下午
 * ---------------------------------------
 * Desc    :
 */
@Slf4j
@RpcService(version = "1.0", corePoolSize = 10, maximumPoolSize = 30, queueSize = 10)
@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseResult<UserPassportEntity> getUserPassport(UserPassportDO userPassportDO) {
        UserPassportEntity userPassportEntity = new UserPassportEntity();
        userPassportEntity.setUserId(userPassportDO.getId());
        userPassportEntity.setPassport(userPassportDO.getPassport());
        userPassportEntity.setPassword(userPassportDO.getPassword());
        userPassportEntity.setCreatedAt(userPassportDO.getCreatedAt());
        userPassportEntity.setToken("哈哈");
        return ResponseResult.success(userPassportEntity);
    }
}
