package com.wx.domain.order.entity.valueobject;

import com.scy.core.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 1:05 下午
 * ---------------------------------------
 * Desc    : 订单状态枚举
 */
@Getter
@ToString
@AllArgsConstructor
public enum OrderStatusEnum {

    /**
     * 订单状态
     */
    WAIT_TO_CONFIRMED(0, "待确认"),

    HAS_BEEN_CONFIRMED(1, "已确认"),
    ;

    private final int status;

    private final String desc;

    public static String getByStatus(int status) {
        return Stream.of(OrderStatusEnum.values())
                .filter(orderStatusEnum -> Objects.equals(orderStatusEnum.getStatus(), status))
                .findFirst().map(OrderStatusEnum::getDesc).orElse(StringUtil.EMPTY);
    }
}
