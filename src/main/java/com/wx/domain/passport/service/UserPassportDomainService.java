package com.wx.domain.passport.service;

import com.scy.core.CollectionUtil;
import com.scy.core.ObjectUtil;
import com.scy.core.encode.Md5Util;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.wx.dao.warehouse.mapper.UserPassportDOMapper;
import com.wx.dao.warehouse.model.UserPassportDO;
import com.wx.dao.warehouse.model.UserPassportDOExample;
import com.wx.domain.passport.entity.UserPassportEntity;
import com.wx.domain.passport.factory.UserPassportFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UserPassportDomainService
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@Slf4j
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

        return UserPassportFactory.toUserPassportEntity(userPassportDO);
    }

    /**
     * 查询所有用户账号信息
     */
    public List<UserPassportEntity> listAllUserPassports() {
        UserPassportDOExample userPassportDOExample = new UserPassportDOExample();
        UserPassportDOExample.Criteria criteria = userPassportDOExample.createCriteria();
        criteria.andIdGreaterThan(0L);
        List<UserPassportDO> userPassports = userPassportDOMapper.selectByExample(userPassportDOExample);
        return userPassports.stream().map(UserPassportFactory::toUserPassportEntity).collect(Collectors.toList());
    }

    public UserPassportEntity checkPassportAndPassword(String passport, String password) {
        UserPassportEntity userPassportEntity = getUserPassport(passport);
        if (ObjectUtil.isNull(userPassportEntity)) {
            log.info(MessageUtil.format("账号不存在", "passport", passport));
            throw new BusinessException("用户名或密码不正确");
        }

        String md5Password = Md5Util.md5Encode(password);
        if (!ObjectUtil.equals(md5Password, userPassportEntity.getPassword())) {
            log.info(MessageUtil.format("密码不正确", "passport", passport, "password", password));
            throw new BusinessException("用户名或密码不正确");
        }

        return userPassportEntity;
    }

    public long insertUserPassport(String passport, String password) {
        UserPassportEntity userPassportEntity = getUserPassport(passport);
        if (!ObjectUtil.isNull(userPassportEntity)) {
            throw new BusinessException("账号已存在");
        }

        UserPassportDO userPassportDO = UserPassportFactory.toUserPassportDO(passport, password);
        userPassportDOMapper.insertSelective(userPassportDO);
        return userPassportDO.getId();
    }

    public long updateUserPassport(String passport, String password) {
        UserPassportEntity userPassportEntity = getUserPassport(passport);
        if (ObjectUtil.isNull(userPassportEntity)) {
            throw new BusinessException("账号信息不存在");
        }

        UserPassportDO userPassportDO = new UserPassportDO();
        userPassportDO.setPassword(Md5Util.md5Encode(password));

        UserPassportDOExample userPassportDOExample = new UserPassportDOExample();
        UserPassportDOExample.Criteria criteria = userPassportDOExample.createCriteria();
        criteria.andIdEqualTo(userPassportEntity.getUserId());
        criteria.andPassportEqualTo(passport);

        userPassportDOMapper.updateByExampleSelective(userPassportDO, userPassportDOExample);
        return userPassportEntity.getUserId();
    }

    public UserPassportEntity getUserPassportEntity(long userId) {
        UserPassportDO userPassportDO = userPassportDOMapper.selectByPrimaryKey(userId);
        return UserPassportFactory.toUserPassportEntity(userPassportDO);
    }

    public Map<Long, String> getAllPassportMap() {
        List<UserPassportEntity> userPassportEntities = listAllUserPassports();
        return userPassportEntities.stream().collect(Collectors.toMap(UserPassportEntity::getUserId, UserPassportEntity::getPassport, (oldValue, newValue) -> newValue));
    }
}
