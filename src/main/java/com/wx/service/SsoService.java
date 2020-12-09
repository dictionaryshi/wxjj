package com.wx.service;

import com.scy.core.StringUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.scy.redis.core.ValueOperationsUtil;
import com.scy.redis.util.RedisUtil;
import com.wx.constant.RedisKeyEnum;
import com.wx.domain.code.entity.CaptchaEntity;
import com.wx.domain.code.service.CaptchaDomainService;
import com.wx.domain.passport.entity.UserPassportEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * SsoService
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@Slf4j
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
        checkCaptcha(captchaId, captcha);
        return userPassportEntity;
    }

    private void checkCaptcha(String captchaId, String captcha) {
        String redisKey = RedisUtil.getRedisKey(RedisKeyEnum.SSO_CAPTCHA.getRedisKeyPrefix(), captchaId);
        String redisCaptcha = valueOperationsUtil.get(redisKey);
        if (StringUtil.isEmpty(redisCaptcha)) {
            log.info(MessageUtil.format("验证码不存在或已过期", "captchaId", captchaId));
            throw new BusinessException("验证码不存在或已过期");
        }

        if (!redisCaptcha.equalsIgnoreCase(captcha)) {
            log.info(MessageUtil.format("验证码不正确", "captcha", captcha, "redisCaptcha", redisCaptcha));
            throw new BusinessException("验证码不正确");
        }
    }
}
