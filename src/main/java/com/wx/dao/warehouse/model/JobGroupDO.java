package com.wx.dao.warehouse.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JobGroupDO {
    /**
     * id
     */
    private Long id;

    /**
     * app标识
     */
    private String appName;

    /**
     * 执行器名称
     */
    private String name;

    /**
     * 0:自动注册, 1:手动录入
     */
    private Integer addressType;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 地址列表
     */
    private String addressList;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAddressList(String addressList) {
        this.addressList = addressList == null ? null : addressList.trim();
    }
}