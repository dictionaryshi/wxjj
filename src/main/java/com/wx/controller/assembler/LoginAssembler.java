package com.wx.controller.assembler;

import com.scy.core.ObjectUtil;
import com.scy.web.model.UserTokenBO;
import com.wx.controller.request.LoginRequest;
import com.wx.domain.passport.entity.UserPassportEntity;

/**
 * LoginAssembler
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/9.
 */
public class LoginAssembler {

    private LoginAssembler() {
    }

    public static UserPassportEntity toUserPassportEntity(LoginRequest loginRequest) {
        UserPassportEntity userPassportEntity = new UserPassportEntity();
        userPassportEntity.setPassport(loginRequest.getPassport());
        userPassportEntity.setPassword(loginRequest.getPassword());
        return userPassportEntity;
    }

    public static UserTokenBO toUserTokenBO(UserPassportEntity userPassportEntity) {
        if (ObjectUtil.isNull(userPassportEntity)) {
            return null;
        }

        UserTokenBO userTokenBO = new UserTokenBO();
        userTokenBO.setToken(userPassportEntity.getToken());
        userTokenBO.setUserId(userPassportEntity.getUserId());
        userTokenBO.setPassport(userPassportEntity.getPassport());
        userTokenBO.setCreatedAt(userPassportEntity.getCreatedAt());
        return userTokenBO;
    }
}
