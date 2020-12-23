package com.wx.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("发送邮件请求对象")
@Getter
@Setter
@ToString
public class SendMailRequest {

    @ApiModelProperty(value = "收件人", required = true)
    @NotBlank(message = "收件人 不为空")
    private String tos;

    @ApiModelProperty(value = "邮件主题", required = true)
    @NotBlank(message = "邮件主题 不为空")
    private String subject;

    @ApiModelProperty(value = "邮件内容", required = true)
    @NotBlank(message = "邮件内容 不为空")
    private String text;

    @ApiModelProperty(value = "附件")
    private MultipartFile file;


    @ApiModelProperty(value = "发件人", hidden = true)
    private String from;

    @ApiModelProperty(value = "文件名称", hidden = true)
    private String fileName;

    @ApiModelProperty(value = "文件 contentType", hidden = true)
    private String contentType;
}
