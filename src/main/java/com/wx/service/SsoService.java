package com.wx.service;

import com.wx.domain.code.entity.CaptchaEntity;
import com.wx.domain.code.service.CaptchaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 获取图片验证码
     */
    public CaptchaEntity getCaptcha() {
        return captchaDomainService.getCaptcha();
    }
}
