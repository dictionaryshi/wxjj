package com.wx.domain.passport.factory;

import com.scy.core.UUIDUtil;
import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.domain.passport.entity.UserPassportEntity;

/**
 * UserPassportFactory
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
public class UserPassportFactory {

    private UserPassportFactory() {
    }

    public static UserPassportEntity toUserPassportEntity(UserPassportDO userPassportDO) {
        UserPassportEntity userPassportEntity = new UserPassportEntity();
        userPassportEntity.setUserId(userPassportDO.getId());
        userPassportEntity.setPassport(userPassportDO.getPassport());
        userPassportEntity.setPassword(userPassportDO.getPassword());
        userPassportEntity.setCreatedAt(userPassportDO.getCreatedAt());
        userPassportEntity.setToken(UUIDUtil.uuid());
        return userPassportEntity;
    }
}
