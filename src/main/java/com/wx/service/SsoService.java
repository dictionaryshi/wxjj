package com.wx.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scy.core.StringUtil;
import com.scy.core.exception.BusinessException;
import com.scy.core.format.MessageUtil;
import com.scy.core.json.JsonUtil;
import com.scy.redis.core.RedisTemplateUtil;
import com.scy.redis.core.ValueOperationsUtil;
import com.scy.redis.util.RedisUtil;
import com.wx.constant.RedisKeyEnum;
import com.wx.domain.code.entity.CaptchaEntity;
import com.wx.domain.code.service.CaptchaDomainService;
import com.wx.domain.passport.entity.UserPassportEntity;
import com.wx.domain.passport.service.UserPassportDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private UserPassportDomainService userPassportDomainService;

    @Autowired
    private ValueOperationsUtil<String, String> valueOperationsUtil;

    @Autowired
    private RedisTemplateUtil<String, String> redisTemplateUtil;

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

        userPassportEntity = userPassportDomainService.checkPassportAndPassword(userPassportEntity.getPassport(), userPassportEntity.getPassword());

        String redisKey = RedisUtil.getRedisKey(RedisKeyEnum.LOGIN_TOKEN.getRedisKeyPrefix(), userPassportEntity.getToken());
        valueOperationsUtil.setIfAbsent(redisKey, JsonUtil.object2Json(userPassportEntity), 3600_000L, TimeUnit.MILLISECONDS);
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

    public UserPassportEntity getLoginUser(String token) {
        String redisKey = RedisUtil.getRedisKey(RedisKeyEnum.LOGIN_TOKEN.getRedisKeyPrefix(), token);
        String json = valueOperationsUtil.get(redisKey);
        if (StringUtil.isEmpty(json)) {
            return null;
        }

        redisTemplateUtil.expire(redisKey, 3600_000L, TimeUnit.MILLISECONDS);

        return JsonUtil.json2Object(json, new TypeReference<UserPassportEntity>() {
        });
    }

    /**
     * 查询所有用户账号信息
     */
    public List<UserPassportEntity> listAllUserPassports() {
        return userPassportDomainService.listAllUserPassports();
    }

    /**
     * 添加用户账号
     */
    public long insertUserPassport(String passport, String password) {
        return userPassportDomainService.insertUserPassport(passport, password);
    }

    /**
     * 修改账号密码
     */
    public long updateUserPassport(String passport, String password) {
        return userPassportDomainService.updateUserPassport(passport, password);
    }
}
