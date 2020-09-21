package com.wx.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

/**
 * SendMailRequest
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/21.
 */
@Getter
@Setter
@ToString
public class SendMailRequest {

    @NotBlank(message = "发件人 不为空")
    private String from;

    @NotBlank(message = "收件人 不为空")
    private String tos;

    @NotBlank(message = "主题 不为空")
    private String subject;

    @NotBlank(message = "邮件内容 不为空")
    private String text;

    private MultipartFile file;
}
