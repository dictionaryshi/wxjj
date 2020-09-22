package com.wx.service;

import com.wx.model.request.SendMailRequest;

/**
 * SendEmailService
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/21.
 */
public interface SendEmailService {

    /**
     * 发送邮件
     *
     * @param sendMailRequest 邮件
     * @throws Throwable 发送异常
     */
    void sendEmail(SendMailRequest sendMailRequest) throws Throwable;
}
