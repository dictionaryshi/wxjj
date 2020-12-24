package com.wx.controller.response;

import com.scy.core.format.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author : shichunyang
 * Date    : 2020/12/22
 * Time    : 9:46 下午
 * ---------------------------------------
 * Desc    : 用户账号
 */
@ApiModel(value = "用户账号信息")
@Getter
@Setter
@ToString
public class UserPassportResponse {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户唯一标识", required = true, example = "123456")
    private Long userId;

    /**
     * 账号
     */
    @ApiModelProperty(value = "用户账号", required = true)
    private String passport;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "用户创建时间", required = true, example = DateUtil.DEFAULT_TIME)
    private Date createdAt;
}
