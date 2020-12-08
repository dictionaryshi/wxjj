package com.wx.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * CaptchaResponse
 *
 * @author shichunyang
 * Created by shichunyang on 2020/12/8.
 */
@ApiModel(value = "图片验证码")
@Setter
@Getter
@ToString
public class CaptchaResponse {

    @ApiModelProperty(value = "图片验证码唯一标识", required = true)
    private String captchaId;

    @ApiModelProperty(value = "图片验证码base64字符串", required = true)
    private String captchaImage;
}
