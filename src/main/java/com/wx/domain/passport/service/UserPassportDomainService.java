package com.wx.domain.passport.service;

import com.scy.core.CollectionUtil;
import com.scy.core.ObjectUtil;
import com.wx.dao.warehouse.mapper.UserPassportDOMapper;
import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.dao.warehouse.model.UserPassportDOExample;
import com.wx.domain.passport.entity.UserPassportEntity;
import com.wx.domain.passport.factory.UserPassportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserPassportDomainService
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@Service
public class UserPassportDomainService {

    @Autowired
    private UserPassportDOMapper userPassportDOMapper;

    public UserPassportEntity getUserPassport(String passport) {
        UserPassportDOExample userPassportDOExample = new UserPassportDOExample();
        UserPassportDOExample.Criteria criteria = userPassportDOExample.createCriteria();
        criteria.andPassportEqualTo(passport);
        List<UserPassportDO> userPassports = userPassportDOMapper.selectByExample(userPassportDOExample);
        UserPassportDO userPassportDO = CollectionUtil.firstElement(userPassports);
        if (ObjectUtil.isNull(userPassportDO)) {
            return null;
        }

        return UserPassportFactory.toUserPassportEntity(userPassportDO);
    }
}
