package com.wx.domain.stock.entity.valueobject;

import com.scy.core.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author : shichunyang
 * Date    : 2021/1/31
 * Time    : 1:12 下午
 * ---------------------------------------
 * Desc    : 库存操作类型
 */
@Getter
@ToString
@AllArgsConstructor
public enum StockTypeEnum {

    /**
     * 出入库状态
     */
    IN(1, "入库"),

    OUT(2, "出库"),

    INIT(3, "盘点"),
    ;

    private final int type;

    private final String desc;

    public static String getByType(int type) {
        return Stream.of(StockTypeEnum.values())
                .filter(stockType -> Objects.equals(stockType.getType(), type))
                .findFirst().map(StockTypeEnum::getDesc).orElse(StringUtil.EMPTY);
    }
}
