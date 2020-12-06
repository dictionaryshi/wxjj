package com.wx.controller.sdk;

import com.scy.core.ObjectUtil;
import com.scy.core.rest.ResponseResult;
import com.scy.web.annotation.SignCheck;
import com.wx.controller.request.SendMailRequest;
import com.wx.service.SendEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * MailController
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/21.
 */
@Slf4j
@Api(tags = "邮件API")
@RequestMapping("/mail")
@RestController
public class MailController {

    private final SendEmailService sendEmailService;

    public MailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @ApiOperation("发送邮件")
    @SignCheck
    @PostMapping(value = "/send-email", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<?> sendEmail(@Valid SendMailRequest sendMailRequest, HttpServletRequest request) throws Throwable {
        sendMailRequest.setFrom("1016496469@qq.com");

        if (!ObjectUtil.isNull(sendMailRequest.getFile())) {
            sendMailRequest.setFileName(sendMailRequest.getFile().getOriginalFilename());
            sendMailRequest.setContentType(request.getServletContext().getMimeType(sendMailRequest.getFile().getOriginalFilename()));
        }

        sendEmailService.sendEmail(sendMailRequest);

        return ResponseResult.success(null);
    }
}
