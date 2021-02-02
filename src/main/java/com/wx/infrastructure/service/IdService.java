package com.wx.infrastructure.service;

import com.scy.core.enums.ResponseCodeEnum;
import com.scy.core.exception.BusinessException;
import com.scy.core.snowflake.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 1:00 下午
 * ---------------------------------------
 * Desc    : IdService
 */
@Service
public class IdService {

    @Autowired
    private Snowflake snowflake;

    public long getId() {
        Long id = snowflake.generate();
        if (Objects.isNull(id)) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "getId error");
        }
        return id;
    }
}
