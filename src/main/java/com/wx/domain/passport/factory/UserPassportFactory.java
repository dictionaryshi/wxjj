package com.wx.domain.passport.factory;

import com.scy.core.ObjectUtil;
import com.scy.core.UUIDUtil;
import com.scy.core.encode.Md5Util;
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
        if (ObjectUtil.isNull(userPassportDO)) {
            return null;
        }

        UserPassportEntity userPassportEntity = new UserPassportEntity();
        userPassportEntity.setUserId(userPassportDO.getId());
        userPassportEntity.setPassport(userPassportDO.getPassport());
        userPassportEntity.setPassword(userPassportDO.getPassword());
        userPassportEntity.setCreatedAt(userPassportDO.getCreatedAt());
        userPassportEntity.setToken(UUIDUtil.uuid());
        return userPassportEntity;
    }

    public static UserPassportDO toUserPassportDO(String passport, String password) {
        UserPassportDO userPassportDO = new UserPassportDO();
        userPassportDO.setPassport(passport);
        userPassportDO.setPassword(Md5Util.md5Encode(password));
        return userPassportDO;
    }
}
