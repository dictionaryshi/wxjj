package com.wx.domain.code.service;

import com.scy.core.ObjectUtil;
import com.wx.domain.code.entity.CaptchaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * CaptchaDomainServiceTest
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@SpringBootTest(classes = CaptchaDomainServiceTest.class)
public class CaptchaDomainServiceTest {

    @InjectMocks
    private CaptchaDomainService captchaDomainService;

    @Test
    public void getCaptcha() {
        CaptchaEntity captcha = captchaDomainService.getCaptcha();
        assert !ObjectUtil.isNull(captcha);
    }
}
