package com.wx.domain.code.service;

import com.scy.core.ObjectUtil;
import com.wx.domain.code.entity.CaptchaEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * CaptchaDomainServiceTest
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@ExtendWith({SpringExtension.class})
public class CaptchaDomainServiceTest {

    @InjectMocks
    private CaptchaDomainService captchaDomainService;

    @Test
    public void getCaptcha() {
        CaptchaEntity captcha = captchaDomainService.getCaptcha();
        assert !ObjectUtil.isNull(captcha);
    }
}
