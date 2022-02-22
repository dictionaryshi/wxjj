package com.wx.netty.service;

import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.domain.passport.entity.UserPassportEntity;

/**
 * @author : shichunyang
 * Date    : 2022/2/18
 * Time    : 3:01 下午
 * ---------------------------------------
 * Desc    : UserService
 */
public interface UserService {

    UserPassportEntity getUserPassport(UserPassportDO userPassportDO);
}
