package com.wx.controller.assembler;

import com.wx.controller.response.CaptchaResponse;
import com.wx.domain.code.entity.CaptchaEntity;

/**
 * CaptchaAssembler
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
public class CaptchaAssembler {

    private CaptchaAssembler() {
    }

    public static CaptchaResponse toCaptchaResponse(CaptchaEntity captchaEntity) {
        CaptchaResponse captchaResponse = new CaptchaResponse();
        captchaResponse.setCaptchaId(captchaEntity.getCaptchaId());
        captchaResponse.setCaptchaImage(captchaEntity.getCaptchaImage());
        return captchaResponse;
    }
}
