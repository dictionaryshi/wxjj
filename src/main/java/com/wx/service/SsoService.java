package com.wx.service;

import com.scy.redis.core.ValueOperationsUtil;
import com.scy.redis.util.RedisUtil;
import com.wx.constant.RedisKeyEnum;
import com.wx.domain.code.entity.CaptchaEntity;
import com.wx.domain.code.service.CaptchaDomainService;
import com.wx.domain.passport.entity.UserPassportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * SsoService
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@Service
public class SsoService {

    @Autowired
    private CaptchaDomainService captchaDomainService;

    @Autowired
    private ValueOperationsUtil<String, String> valueOperationsUtil;

    /**
     * 获取图片验证码
     */
    public CaptchaEntity getCaptcha() {
        CaptchaEntity captchaEntity = captchaDomainService.getCaptcha();

        String redisKey = RedisUtil.getRedisKey(RedisKeyEnum.SSO_CAPTCHA.getRedisKeyPrefix(), captchaEntity.getCaptchaId());
        valueOperationsUtil.setIfAbsent(redisKey, captchaEntity.getCaptcha(), 180_000L, TimeUnit.MILLISECONDS);

        return captchaEntity;
    }

    /**
     * 登录
     */
    public UserPassportEntity login(UserPassportEntity userPassportEntity, String captchaId, String captcha) {
        return userPassportEntity;
    }
}
