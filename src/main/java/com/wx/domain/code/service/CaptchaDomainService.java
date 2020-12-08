package com.wx.domain.code.service;

import com.scy.core.CaptchaUtil;
import com.scy.core.UUIDUtil;
import com.scy.core.encode.Base64Util;
import com.wx.domain.code.entity.CaptchaEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

/**
 * 验证码领域服务
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@Service
public class CaptchaDomainService {

    public CaptchaEntity getCaptcha() {
        CaptchaEntity captchaEntity = new CaptchaEntity();
        captchaEntity.setCaptchaId(UUIDUtil.uuid());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String captcha = CaptchaUtil.getCaptcha(byteArrayOutputStream);
        captchaEntity.setCaptcha(captcha);

        captchaEntity.setCaptchaImage(Base64Util.encodeBase64String(byteArrayOutputStream.toByteArray()));
        return captchaEntity;
    }
}
