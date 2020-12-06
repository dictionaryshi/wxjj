package com.wx.service.impl;

import com.scy.core.ObjectUtil;
import com.scy.core.StringUtil;
import com.wx.controller.request.SendMailRequest;
import com.wx.service.SendEmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * SendEmailService
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/21.
 */
@Slf4j
@Service
public class SendEmailServiceImpl implements SendEmailService {

    private final JavaMailSender javaMailSender;

    public SendEmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(SendMailRequest sendMailRequest) throws Throwable {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, Boolean.TRUE);
        mimeMessageHelper.setTo(StringUtils.split(sendMailRequest.getTos(), StringUtil.COMMA));
        mimeMessageHelper.setFrom(sendMailRequest.getFrom());
        mimeMessageHelper.setSubject(sendMailRequest.getSubject());
        mimeMessageHelper.setText(sendMailRequest.getText(), Boolean.TRUE);
        if (!ObjectUtil.isNull(sendMailRequest.getFile())) {
            mimeMessageHelper.addAttachment(sendMailRequest.getFileName(), sendMailRequest.getFile(), sendMailRequest.getContentType());
        }

        javaMailSender.send(mimeMailMessage);
    }
}
