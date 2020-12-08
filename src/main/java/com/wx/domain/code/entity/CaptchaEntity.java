package com.wx.domain.code.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 验证码实体
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@Getter
@Setter
@ToString
public class CaptchaEntity {

    /**
     * 验证码id
     */
    private String captchaId;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 验证码图片base64字符串
     */
    private String captchaImage;
}
